/**
 * FileName: InstitutionDomainDao
 * Author:   90934
 * Date:     2020/2/7 0:16
 * Description: 认可的签字人及领域
 */

package com.hellof.crawler.dao;

import com.hellof.crawler.pojo.InstitutionDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 90934
 * @date 2020/2/7 0:16
 * @description 认可的签字人及领域Dao层接口
 * @since 0.1.0
 */

public interface InstitutionDomainDao extends JpaRepository<InstitutionDomain, Long> {
}
