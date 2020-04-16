/**
 * FileName: InstitutionUrls
 * Author:   90934
 * Date:     2020/2/4 21:36
 * Description: 读取已更新的机构基本信息url，导入进程
 */

package com.hellof.crawler.io;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 90934
 * @date 2020/2/4 21:36
 * @description 读取txt文档中的地址信息，导入爬虫进程
 * @since 0.1.0
 */

public class GetAddress {
    public static List<String> resultData() {
        List<String> list = new ArrayList<>();
        //读取更新后的机构基本信息url
        String pathname = "classpath:static/address.txt";
        try {
            File file = ResourceUtils.getFile(pathname);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                //向List中添加url
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
