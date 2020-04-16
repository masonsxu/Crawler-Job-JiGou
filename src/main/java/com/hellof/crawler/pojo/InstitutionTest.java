/**
 * FileName: InstitutionTest
 * Author:   90934
 * Date:     2020/2/9 21:14
 * Description: 认可的检验能力范围实体类
 */

package com.hellof.crawler.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 90934
 * @date 2020/2/9 21:14
 * @description 认可的检验能力范围实体类
 * @since 0.1.0
 */
@Entity(name = "institution_test")
public class InstitutionTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iname;
    private String vperiod;
    private String bigtypename;
    private String typename;
    private String num;
    private String fieldch;
    private String detnum;
    private String descriptch;
    private String stdnum;
    private String standardchorder;
    private String restrictch;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getVperiod() {
        return vperiod;
    }

    public void setVperiod(String vperiod) {
        this.vperiod = vperiod;
    }

    public String getBigtypename() {
        return bigtypename;
    }

    public void setBigtypename(String bigtypename) {
        this.bigtypename = bigtypename;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFieldch() {
        return fieldch;
    }

    public void setFieldch(String fieldch) {
        this.fieldch = fieldch;
    }

    public String getDetnum() {
        return detnum;
    }

    public void setDetnum(String detnum) {
        this.detnum = detnum;
    }

    public String getDescriptch() {
        return descriptch;
    }

    public void setDescriptch(String descriptch) {
        this.descriptch = descriptch;
    }

    public String getStdnum() {
        return stdnum;
    }

    public void setStdnum(String stdnum) {
        this.stdnum = stdnum;
    }

    public String getStandardchorder() {
        return standardchorder;
    }

    public void setStandardchorder(String standardchorder) {
        this.standardchorder = standardchorder;
    }

    public String getRestrictch() {
        return restrictch;
    }

    public void setRestrictch(String restrictch) {
        this.restrictch = restrictch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InstitutionTest{" +
                "id=" + id +
                ", iname='" + iname + '\'' +
                ", vperiod='" + vperiod + '\'' +
                ", bigtypename='" + bigtypename + '\'' +
                ", typename='" + typename + '\'' +
                ", num='" + num + '\'' +
                ", fieldch='" + fieldch + '\'' +
                ", detnum='" + detnum + '\'' +
                ", descriptch='" + descriptch + '\'' +
                ", stdnum='" + stdnum + '\'' +
                ", standardchorder='" + standardchorder + '\'' +
                ", restrictch='" + restrictch + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
