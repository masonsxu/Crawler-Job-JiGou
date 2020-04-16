/**
 * FileName: ExportFieldMapper
 * Author:   90934
 * Date:     2020/3/1 19:33
 * Description: 获取相关联的field对象
 */

package com.hellof.crawler.mapper;

import com.hellof.crawler.export.ExportFieldBean;
import org.apache.ibatis.annotations.Select;

/**
 * @author 90934
 * @date 2020/3/1 19:33
 * @description 获取相关联的field对象
 * @since 0.1.0
 */

public interface ExportFieldMapper {
    /**
     * 获取各个field表中的字段信息
     * @param exportid 识别属于表中信息的字段
     * @return n
     */
    @Select("select * from export_field where exportId = #{exportid}")
    ExportFieldBean getExportFieldBeanByExportid(String exportid);
}
