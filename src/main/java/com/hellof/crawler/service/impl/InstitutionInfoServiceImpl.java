/**
 * FileName: InstitutionInfoServiceImpl
 * Author:   90934
 * Date:     2020/2/2 22:57
 * Description: 机构基本信息爬虫项目的Service接口实现类
 */

package com.hellof.crawler.service.impl;

import com.hellof.crawler.dao.InstitutionInfoDao;
import com.hellof.crawler.pojo.InstitutionInfo;
import com.hellof.crawler.service.InstitutionInfoService;
import com.hellof.crawler.websocket.ProductWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author 90934
 */
@Service
public class InstitutionInfoServiceImpl implements InstitutionInfoService {


    private InstitutionInfoDao institutionInfoDao;

    @Autowired
    public void setInstitutionInfoDao(InstitutionInfoDao institutionInfoDao) {
        this.institutionInfoDao = institutionInfoDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(InstitutionInfo institutionInfo) {
        //根据机构名称查询数据
        InstitutionInfo param = new InstitutionInfo();
        param.setName(institutionInfo.getName());

        //执行查询
        List<InstitutionInfo> list = this.findInstitutionInfo(param);

        //打开注释，将爬取的数据显示到web端页面进行查看，注意当爬虫数据过快已造成页面崩溃
        try {
            //判断查询结果是否为空
            if (list.size() == 0) {
                //如果结果为空，表示机构基本信息不存在，需要更新数据库
                this.institutionInfoDao.save(institutionInfo);
                ProductWebSocket.sendInfo("1");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<InstitutionInfo> findInstitutionInfo(InstitutionInfo institutionInfo) {

        //设置查询条件
        Example<InstitutionInfo> example = Example.of(institutionInfo);
        //执行查询
        return this.institutionInfoDao.findAll(example);
    }

    @Override
    public List<InstitutionInfo> findAll() {
        return this.institutionInfoDao.findAll();
    }

}
