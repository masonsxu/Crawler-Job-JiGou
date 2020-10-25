/**
 * FileName: InstitutionInfoService
 * Author:   90934
 * Date:     2020/2/2 22:47
 * Description: 机构基本信息爬虫项目的Service接口层
 */

package com.hellof.crawler.service;

import com.hellof.crawler.pojo.InstitutionInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 90934
 *
 * description 机构的基本信息的service层接口
 */

@Component
public interface InstitutionInfoService {
    /**
     * fetch data by rule id
     *
     * @param institutionInfo rule id
     */
    void save(InstitutionInfo institutionInfo);

    /**
     * fetch data by rule id
     *
     * @param institutionInfo rule id
     * @return Result<institutionInfo>
     */
    List<InstitutionInfo> findInstitutionInfo(InstitutionInfo institutionInfo);

    /**
     * 查询数据库中所有的数据
     *
     * @return Institution
     */
    List<InstitutionInfo> findAll();

}
