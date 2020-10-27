package com.hellof.crawler.task;

import com.hellof.crawler.io.GetAddress;
import com.hellof.crawler.pojo.InstitutionDomain;
import com.hellof.crawler.pojo.InstitutionInfo;
import com.hellof.crawler.pojo.InstitutionTest;
import com.hellof.crawler.service.InstitutionInfoService;
import com.hellof.crawler.util.SpringUtil;
import com.hellof.crawler.websocket.ProductWebSocket;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 90934
 */
@Component
public class JobProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        //判断url的类型
        String queryData = page.getUrl().regex("query\\w+").toString();
        String institutionInfo = "queryOrgInfo1";
        String domainInfo = "queryPublishSignatory";
        String scopeInfo = "queryPublishIBAbilityQuery";
        if (queryData.equals(institutionInfo)) {
            //判断url目的地址是不是queryOrgInfo1
            this.saveInstitutionInfo(page);
        } else if (queryData.equals(domainInfo)) {
            //判断url目的地址是不是queryPublishSignatory
            this.saveDomainInfo(page);
        } else if (queryData.equals(scopeInfo)) {
            //判断url目的地址是不是queryPublishIBAbilityQuery
            this.saveScopeInfo(page);
        } else {
            List<String> urls = new ArrayList<>();
            System.setProperty("webdriver.chrome.driver", "src/main/resources/static/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            String url = page.getUrl().toString();
            driver.get(url);
            //获得配置文件中的省份名称
            // 如果需要爬取个别省份的数据，可以通过修改配置文件重的 address.txt 来获取个别省份的数据
            List<String> addressList = GetAddress.resultData();
            for (String address : addressList) {
                WebElement orgAddress = driver.findElement(By.id("orgAddress"));
                orgAddress.clear();
                orgAddress.sendKeys(address);

                WebElement btn = driver.findElement(By.className("btn"));
                btn.click();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean accept = true;
                while (accept) {
                    try {
                        WebElement pirbutton1 = driver.findElement(By.xpath("//*[@id=\"pirlbutton1\"]"));
                        pirbutton1.click();
                        Thread.sleep(5000);

                        boolean flagStr = driver.findElement(By.id("pirlAuthInterceptDiv_c")).isDisplayed();

                        if (!flagStr) {
                            accept = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                boolean flagStr = driver.findElement(By.xpath("//*[@id=\"pirlAuthInterceptDiv_c\"]")).isDisplayed();
                if (!flagStr) {
                    int maxPage = Integer.parseInt(driver.findElement(By.id("yui-pg0-0-totalPages-span")).getText());
                    for (int num = 0; num < maxPage; num++) {
                        Html html = Html.create(driver.findElement(By.xpath("//*")).getAttribute("outerHTML"));
                        List<Selectable> list = html.css("div.yui-dt-liner a").nodes();
                        if (list.size() != 0) {
                            for (Selectable selectable : list) {
                                //获取id
                                String urlStr = selectable.regex("id\\=\\w+").toString();
                                //组装url放入待爬取队列
                                urls.add("https://las.cnas.org.cn/LAS_FQ/publish/queryOrgInfo1.action?" + urlStr);
                            }
                        }
                        if (num <= maxPage - 2) {
                            WebElement pageNext = driver.findElement(By.xpath("/html/body/div[5]/table/tbody/tr/td[4]/a/img"));
                            pageNext.click();
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            boolean acceptStr = true;
                            while (acceptStr) {
                                try {
                                    WebElement pirbutton1 = driver.findElement(By.xpath("//*[@id=\"pirlbutton1\"]"));
                                    pirbutton1.click();
                                    Thread.sleep(4000);

                                    boolean flagStr1 = driver.findElement(By.id("pirlAuthInterceptDiv_c")).isDisplayed();

                                    if (!flagStr1) {
                                        acceptStr = false;
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
            driver.close();
            driver.quit();
            //将待爬取url放入队列中
            page.addTargetRequests(urls);
        }

    }

    /**
     * 解析页面，获取机构基本信息，保存数据
     */

    private void saveInstitutionInfo(Page page) {
        //创建机构基本信息对象
        InstitutionInfo institutionInfo = new InstitutionInfo();

        //解析页面
        Html html = page.getHtml();

        //获取对象，封装到对象中
        institutionInfo.setName(html.css("div.T1", "text").toString());
        institutionInfo.setRnumber(Jsoup.parse(html.css("span.clabel").nodes().get(0).toString()).text());
        institutionInfo.setOname(Jsoup.parse(html.css("span.clabel").nodes().get(1).toString()).text());
        institutionInfo.setCperson(Jsoup.parse(html.css("span.clabel").nodes().get(2).toString()).text());
        institutionInfo.setCnumber(Jsoup.parse(html.css("span.clabel").nodes().get(3).toString()).text());
        institutionInfo.setPcode(Jsoup.parse(html.css("span.clabel").nodes().get(4).toString()).text());
        institutionInfo.setFnumber(Jsoup.parse(html.css("span.clabel").nodes().get(5).toString()).text());
        institutionInfo.setWeburl(Jsoup.parse(html.css("span.clabel").nodes().get(6).toString()).text());
        institutionInfo.setEmail(Jsoup.parse(html.css("span.clabel").nodes().get(7).toString()).text());
        institutionInfo.setAddress(Jsoup.parse(html.css("span.clabel").nodes().get(8).toString()).text());
        institutionInfo.setStart(Jsoup.parse(html.css("span.clabel").nodes().get(9).toString()).text());
        institutionInfo.setEnd(Jsoup.parse(html.css("span.clabel").nodes().get(10).toString()).text());
        institutionInfo.setAbasis(Jsoup.parse(html.css("span.clabel").nodes().get(11).toString()).text());
        try {
            institutionInfo.setParameter(Jsoup.parse(html.css("span.clabel").nodes().get(12).toString()).text());
        } catch (Exception e) {
            e.printStackTrace();
            institutionInfo.setParameter(null);
        }

        //获取结构化数据的url
        String dataUrl = html.xpath("/html/body/div/table[2]/tbody/tr/td/a/@onclick")
                .regex("foId\\=\\w+").regex("\\=\\w+").regex("[^\\=]\\w+")
                .toString();
        institutionInfo.setBaseinfoid(dataUrl);
        if (dataUrl != null && dataUrl.length() != 0) {
            //认可的授权签字人及领域
            String domainUrl = "https://las.cnas.org.cn/LAS_FQ/publish/queryPublishSignatory.action?baseinfoId=" + dataUrl;
            //放进待爬取队列
            page.addTargetRequest(domainUrl);

            //认可的检验能力范围
            String scopeUrl =
                    "https://las.cnas.org.cn/LAS_FQ/publish/queryPublishIBAbilityQuery.action?baseinfoId=" + dataUrl;
            //放进待爬取队列
            page.addTargetRequest(scopeUrl);
        }
        //把结果保存起来
        page.putField("institutionInfo", institutionInfo);
    }

    /**
     * 装配Service接口，稍后调用查询方法查询相应的字段联系XXX公司和JSon数据的联系
     */
    private InstitutionInfoService institutionInfoService;

    @Autowired
    public void setInstitutionInfoService(InstitutionInfoService institutionInfoService) {
        this.institutionInfoService = institutionInfoService;
    }
    /**
     * 解析josn格式数据，存储认可的授权签字人及领域数据
     */
    private void saveDomainInfo(Page page) {
        //创建一个机构基本信息的实体类查询机构的基本信息，准备整合数据
        InstitutionInfo param = new InstitutionInfo();

        //创建认可的签字人及领域实体类对象
        List<InstitutionDomain> institutionDomains = new ArrayList<>();

        //解析json数据, 分离json数据
        Json json = page.getJson();
        //获取json数据中的baseinfoid的值
        String baseinfoid = page.getUrl().regex("foId\\=\\w+").regex("\\=\\w+").regex("[^\\=]\\w+").toString();
        //获取json数据中的所有姓名的值
        List<String> nameCh = json.jsonPath("$.data[*].nameCh").all();
        //获取json数据中的所有评估说明的值
        List<String> content = json.jsonPath("$.data[*].assessmentcontent").all();
        //获取json数据中的所有授权签字领域的值
        List<String> authorizedFieldCh = json.jsonPath("$.data[*].authorizedFieldCh").all();
        //获取json数据中的所有说明的值
        List<String> note = json.jsonPath("$.data[*].note").all();
        //获取json数据中的所有状态的值status
        List<String> status;
        status = json.jsonPath("$.data[*].status").all();

        param.setBaseinfoid(baseinfoid);
        List<InstitutionInfo> institutionInfos = getService().institutionInfoService.findInstitutionInfo(param);
        String name;
        String date;
        if (institutionInfos.size() != 0) {
            InstitutionInfo institutionInfo = institutionInfos.get(0);
            name = institutionInfo.getName();
            date = institutionInfo.getStart() + "-" + institutionInfo.getEnd();
            for (int num = 0; num < nameCh.size(); num++) {
                //创建认可的签字人及领域实体类对象
                InstitutionDomain institutionDomain = new InstitutionDomain();

                institutionDomain.setIname(name);
                institutionDomain.setVperiod(date);
                institutionDomain.setName(nameCh.get(num));
                institutionDomain.setContent(content.get(num));
                institutionDomain.setDomain(authorizedFieldCh.get(num));
                institutionDomain.setDescription(note.get(num));
                if ("0".equals(status.get(num))) {
                    institutionDomain.setStatus("有效");
                } else {
                    institutionDomain.setStatus("无效");
                }
                institutionDomains.add(institutionDomain);
            }
            page.putField("institutionDomains", institutionDomains);
        }
    }

    /**
     * 解析josn格式数据，存储认可的检验能力范围数据
     */
    private void saveScopeInfo(Page page) {
        //创建一个机构基本信息的实体类查询机构的基本信息，准备整合数据
        InstitutionInfo param = new InstitutionInfo();

        List<InstitutionTest> institutionTests = new ArrayList<>();

        Json json = page.getJson();
        //获取json数据中的baseinfoid的值
        String baseinfoid = page.getUrl().regex("foId\\=\\w+").regex("\\=\\w+").regex("[^\\=]\\w+").toString();
        List<String> bigTypeName = json.jsonPath("$.data[*].bigTypeName").all();
        List<String> typeName = json.jsonPath("$.data[*].typeName").all();
        List<String> num = json.jsonPath("$.data[*].num").all();
        List<String> fieldch = json.jsonPath("$.data[*].fieldch").all();
        List<String> detnum = json.jsonPath("$.data[*].detnum").all();
        List<String> descriptCh = json.jsonPath("$.data[*].descriptCh").all();
        List<String> stdNum = json.jsonPath("$.data[*].stdNum").all();
        List<String> standardCh = json.jsonPath("$.data[*].standardCh").all();
        List<String> order = json.jsonPath("$.data[*].order").all();
        List<String> restrictCh = json.jsonPath("$.data[*].restrictCh").all();
        List<String> status = json.jsonPath("$.data[*].status").all();

        param.setBaseinfoid(baseinfoid);
        List<InstitutionInfo> institutionInfos = getService().institutionInfoService.findInstitutionInfo(param);
        String name;
        String date;
        if (institutionInfos.size() != 0) {
            InstitutionInfo institutionInfo = institutionInfos.get(0);
            name = institutionInfo.getName();
            date = institutionInfo.getStart() + "-" + institutionInfo.getEnd();
            for (int i = 0; i < fieldch.size(); i++) {
                InstitutionTest institutionTest = new InstitutionTest();
                institutionTest.setIname(name);
                institutionTest.setVperiod(date);
                institutionTest.setBigtypename(bigTypeName.get(i));
                institutionTest.setTypename(typeName.get(i));
                institutionTest.setNum(num.get(i));
                institutionTest.setFieldch(fieldch.get(i));
                institutionTest.setDetnum(detnum.get(i));
                institutionTest.setDescriptch(descriptCh.get(i));
                institutionTest.setStdnum(stdNum.get(i));
                institutionTest.setStandardchorder(standardCh.get(i) + " " + order.get(i));
                institutionTest.setRestrictch(restrictCh.get(i));
                if ("0".equals(status.get(i))) {
                    institutionTest.setStatus("有效");
                } else {
                    institutionTest.setStatus("无效");
                }
                institutionTests.add(institutionTest);
            }
            page.putField("institutionTests", institutionTests);
        }

    }

    private Site site = Site.me()
            //设置编码格式
            .setCharset("utf8")
            //设置超时时间
            .setTimeOut(10 * 1000)
            //设置重试的间隔时间
            .setRetrySleepTime(3 * 1000)
            //设置重试的次数
            .setRetryTimes(3);

    @Override
    public Site getSite() {
        return site;
    }

    private InstitutionInfoPipeline institutionInfoPipeline;
    private InstitutionDomainPipeline institutionDomainPipeline;
    private InstitutionTestPipeline institutionTestPipeline;

    @Autowired
    public void setInstitutionInfoPipeline(InstitutionInfoPipeline institutionInfoPipeline) {
        this.institutionInfoPipeline = institutionInfoPipeline;
    }

    @Autowired
    public void setInstitutionDomainPipeline(InstitutionDomainPipeline institutionDomainPipeline) {
        this.institutionDomainPipeline = institutionDomainPipeline;
    }

    @Autowired
    public void setInstitutionTestPipeline(InstitutionTestPipeline institutionTestPipeline) {
        this.institutionTestPipeline = institutionTestPipeline;
    }

    /**
     * 爬虫的启动方法
     */
    public void start(PageProcessor pageProcessor) {
        //开启线程
        Spider.create(pageProcessor)
                //添加初始url
                .addUrl("https://las.cnas.org.cn/LAS/publish/externalQueryIB.jsp")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .addPipeline(this.institutionInfoPipeline)
                .addPipeline(this.institutionDomainPipeline)
                .addPipeline(this.institutionTestPipeline)
                .thread(10)
                .run();
        try {
            ProductWebSocket.sendInfo("爬虫采集已结束,请在数据库中进行查看,或导出为Excel格式进行查看！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return SpringUtil工具类
     */

    private JobProcessor getService() {
        return SpringUtil.getBean(this.getClass());
    }
}
