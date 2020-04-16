package com.hellof.crawler.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 〈一句话功能简述〉<br>
 * 〈机构基本信息〉
 *
 * @author 90934
 * @create 2020/2/2
 * @since 1.0.0
 */
@Entity(name = "institution_info")
public class InstitutionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rnumber;
    private String oname;
    private String cperson;
    private String cnumber;
    private String pcode;
    private String fnumber;
    private String weburl;
    private String email;
    private String address;
    private String start;
    private String end;
    private String abasis;
    private String parameter;
    private String baseinfoid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRnumber() {
        return rnumber;
    }

    public void setRnumber(String rnumber) {
        this.rnumber = rnumber;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getCperson() {
        return cperson;
    }

    public void setCperson(String cperson) {
        this.cperson = cperson;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAbasis() {
        return abasis;
    }

    public void setAbasis(String abasis) {
        this.abasis = abasis;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getBaseinfoid() {
        return baseinfoid;
    }

    public void setBaseinfoid(String baseinfoid) {
        this.baseinfoid = baseinfoid;
    }

    @Override
    public String toString() {
        return "InstitutionInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rnumber='" + rnumber + '\'' +
                ", oname='" + oname + '\'' +
                ", cperson='" + cperson + '\'' +
                ", cnumber='" + cnumber + '\'' +
                ", pcode='" + pcode + '\'' +
                ", fnumber='" + fnumber + '\'' +
                ", weburl='" + weburl + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", abasis='" + abasis + '\'' +
                ", parameter='" + parameter + '\'' +
                ", baseinfoid='" + baseinfoid + '\'' +
                '}';
    }
}
