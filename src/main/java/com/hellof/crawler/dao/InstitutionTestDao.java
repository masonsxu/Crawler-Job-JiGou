/**
 * FileName: InstitutionTestDao
 * Author:   90934
 * Date:     2020/2/9 21:21
 * Description: 认可的检验能力范围
 */

package com.hellof.crawler.dao;

import com.hellof.crawler.pojo.InstitutionTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 90934
 * @date 2020/2/9 21:21
 * @description 认可的检验能力范围
 * @since 0.1.0
 */

public interface InstitutionTestDao extends JpaRepository<InstitutionTest, Long> {
}
