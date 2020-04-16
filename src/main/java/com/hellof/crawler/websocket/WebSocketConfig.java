/**
 * FileName: ServerEndpointExporter
 * Author:   90934
 * Date:     2020/2/27 13:08
 * Description: websocket的configuration配置文件
 */

package com.hellof.crawler.websocket;

/**
 * @author 90934
 * @date 2020/2/27 13:08
 * @description websocket的configuration配置文件
 * @since 0.1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public MyEndpointConfigure newConfigure() {
        return new MyEndpointConfigure();
    }
}
