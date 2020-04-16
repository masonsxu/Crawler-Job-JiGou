/**
 * FileName: InstitutionTestPipeline
 * Author:   90934
 * Date:     2020/2/9 21:41
 * Description: 自定义的认可的检验能力范围pipeline通道
 */

package com.hellof.crawler.task;


import com.hellof.crawler.pojo.InstitutionTest;
import com.hellof.crawler.service.InstitutionTestService;
import com.hellof.crawler.websocket.ProductWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author 90934
 * @date 2020/2/9 21:41
 * @description 自定义的认可的检验能力范围pipeline通道
 * @since 0.1.0
 */
@Component
public class InstitutionTestPipeline implements Pipeline {
    private InstitutionTestService institutionTestService;

    @Autowired
    public void setInstitutionTestService(InstitutionTestService institutionTestService) {
        this.institutionTestService = institutionTestService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的pojo实体类
        List<InstitutionTest> institutionTests = resultItems.get("institutionTests");
        if (institutionTests != null){
            this.institutionTestService.save(institutionTests);
        }
    }
}
