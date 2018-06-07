package com.wen.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocket = new CopyOnWriteArraySet();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocket.add(this);
        log.info("【有新的连接接入】 连接的个数为{}",webSocket.size());
    }

    @OnClose
    public void onClose() {
        webSocket.remove(this);
        log.info("【连接断开】 连接个数为{}", webSocket.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【接收到客户端的消息】：{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket1 : webSocket) {
            log.info("【广播消息】 : {}", message);
            try {
                webSocket1.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
