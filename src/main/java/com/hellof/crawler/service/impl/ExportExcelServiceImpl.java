/**
 * FileName: ExportExcelServiceImpl
 * Author:   90934
 * Date:     2020/2/29 22:58
 * Description: 导出文件的方法实现类
 */

package com.hellof.crawler.service.impl;

import com.hellof.crawler.export.ExportFieldBean;
import com.hellof.crawler.mapper.ExportMapper;
import com.hellof.crawler.service.IExportExcelService;
import com.hellof.crawler.websocket.ProductWebSocket;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author 90934
 * @date 2020/2/29 22:58
 * @description 导出文件的方法实现类
 * @since 0.1.0
 */
@Service
public class ExportExcelServiceImpl implements IExportExcelService {
    private ExportMapper exportMapper;

    @Resource
    public void setExportMapper(ExportMapper exportMapper) {
        this.exportMapper = exportMapper;
    }

    @Override
    public void exportExcelWithDispose(String exportKey, String fileName, List<?> list, HttpServletRequest req, HttpServletResponse resp) {
        List<ExportFieldBean> fieldBeans = this.exportMapper.getExportByExportKey(exportKey).getFieldBeanList();
        try {
            SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
            SXSSFSheet sheet1 = sxssfWorkbook.createSheet(fileName);
            SXSSFRow headRow = sheet1.createRow(0);
            headRow.createCell(0).setCellValue("序号");
            for (ExportFieldBean fieldBean: fieldBeans){
                headRow.createCell(headRow.getLastCellNum()).setCellValue(fieldBean.getFieldName());
            }
            int index = 0;
            SXSSFRow bodyRow = null;
            JSONArray jsonArray = JSONArray.fromObject(list);
            for (Object obj:jsonArray){
                bodyRow = sheet1.createRow(sheet1.getLastRowNum() + 1);
                bodyRow.createCell(0).setCellValue(index++);
                int flag = 0;
                for (ExportFieldBean fieldBean: fieldBeans){
                    if (flag == 0){
                        bodyRow.createCell(bodyRow.getLastCellNum()).setCellValue((Integer) ((JSONObject)obj).get(fieldBean.getFieldCode()));
                        flag = 1;
                    }else {
                        bodyRow.createCell(bodyRow.getLastCellNum()).setCellValue((String) ((JSONObject)obj).get(fieldBean.getFieldCode()));
                    }

                }
            }
            FileOutputStream outputStream = new FileOutputStream(fileName + ".xlsx");
            sxssfWorkbook.write(outputStream);
            outputStream.close();
            sxssfWorkbook.close();
            //打开注释，将爬取的数据显示到web端页面进行查看，注意当爬虫数据过快已造成页面崩溃
            ProductWebSocket.sendInfo("已成功导出 " + list.size() + " 条数据！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
