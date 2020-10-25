/**
 * FileName: InstitutionTestServiceImpl
 * Author:   90934
 * Date:     2020/2/9 21:28
 * Description: 认可的检验能力范围Service方法实现类
 */

package com.hellof.crawler.service.impl;

import com.hellof.crawler.dao.InstitutionTestDao;
import com.hellof.crawler.pojo.InstitutionTest;
import com.hellof.crawler.service.InstitutionTestService;
import com.hellof.crawler.websocket.ProductWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author 90934
 * @date 2020/2/9 21:28
 * @description 认可的检验能力范围Service方法实现类
 * @since 0.1.0
 */
@Service
public class InstitutionTestServiceImpl implements InstitutionTestService {
    private InstitutionTestDao institutionTestDao;

    @Autowired
    public void setInstitutionTestDao(InstitutionTestDao institutionTestDao) {
        this.institutionTestDao = institutionTestDao;
    }

    @Override
    public void save(List<InstitutionTest> institutionTests) {
        //根据所属机构名称、检验对象序号和检验对象进行查询
        InstitutionTest param = new InstitutionTest();

        try {
            //遍历JSON数组
            for (InstitutionTest institutionTest : institutionTests) {
                param.setIname(institutionTest.getIname());
                param.setNum(institutionTest.getNum());
                param.setFieldch(institutionTest.getFieldch());
                param.setDetnum(institutionTest.getDetnum());
                param.setDescriptch(institutionTest.getDescriptch());
                param.setStdnum(institutionTest.getStdnum());
                param.setStandardchorder(institutionTest.getStandardchorder());
                //执行查询
                List<InstitutionTest> list = this.findInstitutionTest(param);
                if (list.size() == 0) {
                    this.institutionTestDao.save(institutionTest);
                    ProductWebSocket.sendInfo("1");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InstitutionTest> findInstitutionTest(InstitutionTest institutionTest) {
        //设置查询条件
        Example<InstitutionTest> example = Example.of(institutionTest);
        return this.institutionTestDao.findAll(example);
    }

    @Override
    public List<InstitutionTest> findAll() {
        return this.institutionTestDao.findAll();
    }

}
