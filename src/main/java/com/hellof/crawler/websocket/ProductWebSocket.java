/**
 * FileName: MyWebsocketServer
 * Author:   90934
 * Date:     2020/2/28 12:10
 * Description: WebSocket服务端
 */

package com.hellof.crawler.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 90934
 * @date 2020/2/28 12:10
 * @description WebSocket服务端
 * @since 0.1.0
 */
@ServerEndpoint("/")
@Component
@Slf4j
public class ProductWebSocket {
    /**
     * 静态变量，用来记录当前在线连接数，应该把设计成线程安全的
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
    /**
     * concurrent包的线程安全set，用来存放每个客户端对应的ProductWebSocket对象。
     */
    private static final CopyOnWriteArrayList<ProductWebSocket> webSocketSet = new CopyOnWriteArrayList<>();
    /**
     * 与某个客户端的链接会话，需要通过它来给客户发送数据
     */
    private volatile Session session;

    /**
     * 链接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        log.info("开始爬虫进程，请稍后");
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        if (userId != null) {
            List<String> totalPushMsgs = new ArrayList<String>();
            totalPushMsgs.add("爬虫启动成功--当前爬虫启动数量为: " + getOnlineCount());
            if (!totalPushMsgs.isEmpty()) {
                totalPushMsgs.forEach(this::sendMessage);
            }
        }
    }

    /**
     * 连接关闭调用方法
     */
    @OnClose
    public void onClose() {
        log.info("爬虫已关闭");
        webSocketSet.remove(this);
        subOnlineCount();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("当前爬取的数据为: " + message + session);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("websocket出现错误!");
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        try {
            synchronized (this.session){
                this.session.getBasicRemote().sendText(message);
                log.info("数据获取成功，数据为: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多数据返回方法
     */
    public static void sendInfo(String message) throws IOException {
        for (ProductWebSocket productWebSocket : webSocketSet) {
            productWebSocket.sendMessage(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return ONLINE_COUNT.get();
    }

    /**
     * 爬虫启动数量加一
     */
    public static synchronized void addOnlineCount() {
        ONLINE_COUNT.incrementAndGet();
    }

    /**
     * 爬虫启动数减一
     */
    public static synchronized void subOnlineCount() {
        ONLINE_COUNT.decrementAndGet();
    }
}
