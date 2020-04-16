/**
 * FileName: InstitutionDomainServiceImpl
 * Author:   90934
 * Date:     2020/2/7 0:22
 * Description: 认可的签字人及领域方法实现层
 */

package com.hellof.crawler.service.impl;

import com.hellof.crawler.dao.InstitutionDomainDao;
import com.hellof.crawler.pojo.InstitutionDomain;
import com.hellof.crawler.service.InstitutionDomainService;
import com.hellof.crawler.websocket.ProductWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author 90934
 * @date 2020/2/7 0:22
 * @description 认可的签字人及领域方法实现层
 * @since 0.1.0
 */

@Service
public class InstitutionDomainServiceImpl implements InstitutionDomainService {

    private InstitutionDomainDao institutionDomainDao;

    @Autowired
    public void setInstitutionDomain(InstitutionDomainDao institutionDomainDao) {
        this.institutionDomainDao = institutionDomainDao;
    }

    @Override
    public void save(List<InstitutionDomain> institutionDomains) {
        //根据签字人姓名和所属机构查询数据
        InstitutionDomain param = new InstitutionDomain();
        //遍历json数组
        for (InstitutionDomain institutionDomain : institutionDomains) {
            param.setIname(institutionDomain.getIname());
            param.setName(institutionDomain.getName());
            //执行查询
            List<InstitutionDomain> list = this.findInstitutionDomain(param);
            //判断查询结果是否为空
            if (list.size() == 0) {
                this.institutionDomainDao.save(institutionDomain);
            }
            //打开注释，将爬取的数据显示到web端页面进行查看，注意当爬虫数据过快已造成页面崩溃
            try {
                ProductWebSocket.sendInfo("已成功采集 " + institutionDomains.size() + " 条数据！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<InstitutionDomain> findInstitutionDomain(InstitutionDomain institutionDomain) {
        //设置查询条件
        Example<InstitutionDomain> example = Example.of(institutionDomain);
        return this.institutionDomainDao.findAll(example);
    }

    @Override
    public List<InstitutionDomain> findAll() {
        return this.institutionDomainDao.findAll();
    }

}
