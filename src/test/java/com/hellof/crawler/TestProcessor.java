/**
 * FileName: TestProcessor
 * Author:   90934
 * Date:     2020/3/2 18:25
 * Description: 重写PageProcessor方法
 */

package com.hellof.crawler;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author 90934
 * @date 2020/3/2 18:25
 * @description 重写PageProcessor方法
 * @since 0.1.0
 */
@Component
public class TestProcessor implements PageProcessor {
    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return null;
    }
}
