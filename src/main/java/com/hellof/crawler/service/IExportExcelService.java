/**
 * FileName: IExportExcelService
 * Author:   90934
 * Date:     2020/2/29 22:49
 * Description: 导出为excel格式的方法的接口
 */

package com.hellof.crawler.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 90934
 * @date 2020/2/29 22:49
 * @description 导出为excel格式的方法的接口
 * @since 0.1.0
 */

public interface IExportExcelService {
    /**
     * 根据exportKey查询需要导出的字段，并匹配list每个类中字段来到出excel
     * @param exportKey 数据库存储的导出英文名
     * @param fileName 文件名
     * @param list 要到出的数据
     * @param req 请求
     * @param resp 响应
     */
    public void exportExcelWithDispose(String exportKey, String fileName, List<?> list, HttpServletRequest req,
                                       HttpServletResponse resp);
}
