/**
 * FileName: InstitutionTestService
 * Author:   90934
 * Date:     2020/2/9 21:23
 * Description: 认可的检验能力范围Service接口
 */

package com.hellof.crawler.service;

import com.hellof.crawler.pojo.InstitutionTest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 90934
 * @date 2020/2/9 21:23
 * @description 认可的检验能力范围Service接口
 * @since 0.1.0
 */
@Component
public interface InstitutionTestService {
    /**
     * fetch data by rule id
     *
     * @param institutionTests rule id
     */
    void save(List<InstitutionTest> institutionTests);

    /**
     * fetch data by rule id
     *
     * @param institutionTest rule id
     * @return Result<institutionTest>
     */
    List<InstitutionTest> findInstitutionTest(InstitutionTest institutionTest);

    /**
     * 查询数据库中所有的数据
     *
     * @return InstitutionTest
     */
    List<InstitutionTest> findAll();

}
