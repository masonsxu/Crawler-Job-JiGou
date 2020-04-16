/**
 * FileName: ExportMapper
 * Author:   90934
 * Date:     2020/2/29 23:51
 * Description: 导出字段的方法接口
 */

package com.hellof.crawler.mapper;

import com.hellof.crawler.export.ExportBean;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author 90934
 * @date 2020/2/29 23:51
 * @description 导出字段的方法接口
 * @since 0.1.0
 */
public interface ExportMapper {
    /**
     * 获取各个字段的名称
     * @param exportKey 字段名称
     * @return ExportBean
     */
    @Select("select * from export where exportCode = #{exportKey}")
    @Results({
            @Result(property="fieldBeanList",column="id",one=@One(select="com.hellof.crawler.mapper.ExportFieldMapper" +
                    ".getExportFieldBeanByExportid"))
    })
    ExportBean getExportByExportKey(String exportKey);
}
