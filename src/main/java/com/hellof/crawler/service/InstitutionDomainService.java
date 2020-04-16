/**
 * FileName: InstitutionDomainService
 * Author:   90934
 * Date:     2020/2/7 0:19
 * Description: 认可的签字人及领域的service层
 */

package com.hellof.crawler.service;

import com.hellof.crawler.pojo.InstitutionDomain;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 90934
 * @date 2020/2/7 0:19
 * @description 认可的签字人及领域的service层
 * @since 0.1.0
 */
@Component
public interface InstitutionDomainService {
    /**
     * fetch data by rule id
     *
     * @param institutionDomains rule id
     */
    void save(List<InstitutionDomain> institutionDomains);

    /**
     * fetch data by rule id
     *
     * @param institutionDomain rule id
     * @return Result<institutionDomain>
     */
    List<InstitutionDomain> findInstitutionDomain(InstitutionDomain institutionDomain);

    /**
     * 查询数据库中所有的数据
     *
     * @return InstitutionDomain
     */
    List<InstitutionDomain> findAll();
}
