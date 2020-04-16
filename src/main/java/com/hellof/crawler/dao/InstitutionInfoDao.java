/**
 * FileName: InstitutionInfoDao
 * Author:   90934
 * Date:     2020/2/2 21:12
 * Description: 机构基本信息的Dao层接口
 * History:
 */

package com.hellof.crawler.dao;

import com.hellof.crawler.pojo.InstitutionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 90934
 */
public interface InstitutionInfoDao extends JpaRepository<InstitutionInfo, Long> {
}
