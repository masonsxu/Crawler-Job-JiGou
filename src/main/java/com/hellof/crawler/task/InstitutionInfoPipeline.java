/**
 * FileName: SpringDataPipeline
 * Author:   90934
 * Date:     2020/2/3 18:31
 * Description: 定制的pipeline类
 */

package com.hellof.crawler.task;

import com.hellof.crawler.pojo.InstitutionInfo;
import com.hellof.crawler.service.InstitutionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author 90934
 * @date 2020/2/3 18:31
 * @description 定制的pipeline类
 * @since 0.1.0
 */
@Component
public class InstitutionInfoPipeline implements Pipeline {

    private InstitutionInfoService institutionInfoService;

    @Autowired
    public void setInstitutionInfoService(InstitutionInfoService institutionInfoService) {
        this.institutionInfoService = institutionInfoService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的机构基本信息对象
        InstitutionInfo institutionInfo = resultItems.get("institutionInfo");

        //判断数据是否为不空
        if (institutionInfo != null) {
            //如果不为空把数据保存在数据库中
            this.institutionInfoService.save(institutionInfo);
        }
    }
}
