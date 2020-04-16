/**
 * FileName: ExportFiledBean
 * Author:   90934
 * Date:     2020/2/29 23:49
 * Description: 导出字段表
 */

package com.hellof.crawler.export;

/**
 * @author 90934
 * @date 2020/2/29 23:49
 * @description 导出字段表
 * @since 0.1.0
 */

public class ExportFieldBean {
    private Integer id;

    private Integer exportId;

    private String fieldCode;

    private String fieldName;

    private Integer sort;

    private ExportBean exportBean;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExportId() {
        return exportId;
    }

    public void setExportId(Integer exportId) {
        this.exportId = exportId;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public ExportBean getExportBean() {
        return exportBean;
    }

    public void setExportBean(ExportBean exportBean) {
        this.exportBean = exportBean;
    }
}
