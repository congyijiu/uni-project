package com.uni.pj.ws;

import com.alibaba.fastjson.JSON;
import com.uni.pj.config.CustomConfigurator;
import com.uni.pj.service.BigModleService;
import com.uni.pj.service.ChatMessageService;
import com.uni.pj.ws.dtos.ChatMessageDto;
import com.uni.pj.ws.pojo.ChatMessage;
import com.uni.pj.ws.vos.MessageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.*;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author congyijiu
 * @create 2023-12-06-14:53
 */
@Component
@ServerEndpoint(value = "/websocket/{userId}")
@Slf4j
@Tag(name = "WebSocket相关接口")
public class WebSocket {

    private static ChatMessageService chatMessageService;

    private static BigModleService bigModleService;

    // 注入的时候，给类的 service 注入
    @Autowired
    public void setChatService(ChatMessageService chatMessageService) {
        WebSocket.chatMessageService = chatMessageService;
    }

    @Autowired
    public void setChatService(BigModleService bigModleService) {
        WebSocket.bigModleService = bigModleService;
    }

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<Integer, WebSocket> clients = new ConcurrentHashMap<Integer, WebSocket>();

    private Session session;

    private Integer userId;

    /**
     * 会话
     * @param userId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {

        session.setMaxTextMessageBufferSize(10*1024*1024);
        session.setMaxBinaryMessageBufferSize(10*1024*1024);

        this.session = session;
        this.userId = Integer.parseInt(userId);



        if (clients.containsKey(Integer.parseInt(userId))) {
            clients.remove(Integer.parseInt(userId));
            clients.put(Integer.parseInt(userId), this);
        } else {
            clients.put(Integer.parseInt(userId), this);
            onlineNumber++;
        }
        log.info("连接成功，连接用户ID:" + userId + ",当前在线人数为:" + onlineNumber);
//        MessageVo messageVo = new MessageVo();
//        messageVo.setSendUserId(0);
//        messageVo.setType("text");
//        messageVo.setContent("连接成功");
//        String jsonString = JSON.toJSONString(messageVo);
//        this.sendMessage(jsonString);
    }


    @OnError
    public void onError(Throwable throwable) {
        System.out.println("连接失败");
    }

    @OnClose
    public void onClose() {
        if (clients.containsKey(this.userId)) {
            clients.remove(this.userId);
            onlineNumber--;
            log.info("有连接关闭，关闭用户ID:"+ this.userId +",当前在线人数为:" + onlineNumber);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        ChatMessageDto messageDto = JSON.parseObject(message, ChatMessageDto.class);
        log.info("收到来自用户ID为：" + this.userId + "的消息:" + messageDto.getContent());

        if(messageDto.getAcceptUserId()!=0){
            messageDto.setSendUserId(this.userId);
            chatMessageService.addFriendMsg(messageDto);
            log.info("保存消息成功");
        }
        // 判断是否是发给机器人
        if (messageDto.getAcceptUserId()==27){
//            // 调用机器人接口
//            String question = bigModleService.sendQuestion(messageDto.getContent(),this.userId);
//            // 封装消息
//            MessageVo messageVo = new MessageVo();
//            messageVo.setSendUserId(0);
//            messageVo.setType("text");
//            messageVo.setContent(question);
//            String jsonString = JSON.toJSONString(messageVo);
//
//            //封装消息
//            ChatMessageDto chatMessageDto = new ChatMessageDto();
//            chatMessageDto.setSendUserId(27);
//            chatMessageDto.setAcceptUserId(this.userId);
//            chatMessageDto.setContent(question);
//            chatMessageDto.setType("text");
//            // 将机器人的回复存入数据库
//            chatMessageService.addFriendMsg(chatMessageDto);
//            log.info("保存机器人回复成功");
//
//            // 返回消息
//            this.sendMessage(jsonString);
//            log.info("发送机器人回复成功");
        }else if (clients.containsKey(messageDto.getAcceptUserId())) {
            // 封装消息
            MessageVo messageVo = new MessageVo();
            messageVo.setSendUserId(this.userId);
            messageVo.setType(messageDto.getType());
            messageVo.setContent(messageDto.getContent());
            messageVo.setImageBase64(messageDto.getImageBase64());
            String jsonString = JSON.toJSONString(messageVo);

            //加上后缀，标志分片结束标志
            jsonString = jsonString + "-MessageInEnd";

            // 发送消息
            clients.get(messageDto.getAcceptUserId()).sendMessage(jsonString);
        }
    }

    private void sendMessage(String content) {
        try {
            this.session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        System.err.println("WebSocket错误: " + throwable.getMessage());
//        throwable.printStackTrace();
//    }



}




