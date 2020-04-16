/**
 * FileName: InstitutionDomain
 * Author:   90934
 * Date:     2020/2/7 0:11
 * Description: 认可的签字人及领域的信息
 */

package com.hellof.crawler.pojo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 90934
 * @date 2020/2/7 0:11
 * @description 认可的签字人及领域的信息
 * @since 0.1.0
 */

@Entity(name = "institution_domain")
public class InstitutionDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iname;
    private String vperiod;
    private String name;
    private String content;
    private String domain;
    private String description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InstitutionDomain{" +
                "id=" + id +
                ", iname='" + iname + '\'' +
                ", vperiod='" + vperiod + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", domain='" + domain + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
