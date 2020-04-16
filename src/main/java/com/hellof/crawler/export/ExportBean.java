/**
 * FileName: ExportBean
 * Author:   90934
 * Date:     2020/2/29 23:47
 * Description: 导出主题表
 */

package com.hellof.crawler.export;

import java.util.List;

/**
 * @author 90934
 * @date 2020/2/29 23:47
 * @description 导出主题表
 * @since 0.1.0
 */

public class ExportBean {

    private Integer id;

    private String exportCode;

    private String exportName;

    private List<ExportFieldBean> fieldBeanList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExportCode() {
        return exportCode;
    }

    public void setExportCode(String exportCode) {
        this.exportCode = exportCode;
    }

    public String getExportName() {
        return exportName;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public List<ExportFieldBean> getFieldBeanList() {
        return fieldBeanList;
    }

    public void setFieldBeanList(List<ExportFieldBean> fieldBeanList) {
        this.fieldBeanList = fieldBeanList;
    }
}
