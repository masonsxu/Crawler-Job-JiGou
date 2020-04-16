/**
 * FileName: InstitutionDomainPipeline
 * Author:   90934
 * Date:     2020/2/7 12:27
 * Description: 定制认可的签字人及领域pipeline类
 */

package com.hellof.crawler.task;

import com.hellof.crawler.pojo.InstitutionDomain;
import com.hellof.crawler.service.InstitutionDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author 90934
 * @date 2020/2/7 12:27
 * @description 定制认可的签字人及领域pipeline类
 * @since 0.1.0
 */
@Component
public class InstitutionDomainPipeline implements Pipeline {
    private InstitutionDomainService institutionDomainService;

    @Autowired
    public void setInstitutionDomainService(InstitutionDomainService institutionDomainService) {
        this.institutionDomainService = institutionDomainService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的pojo对象
        List<InstitutionDomain> institutionDomains = resultItems.get("institutionDomains");
        //判断数据是否为不空
        if (institutionDomains != null) {
            this.institutionDomainService.save(institutionDomains);
        }
    }
}
