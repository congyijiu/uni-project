package com.uni.pj.config;

/**
 * @author congyijiu
 * @create 2024-06-10-12:13
 */
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

public class CustomConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().put("org.apache.tomcat.websocket.textBufferSize", 16 * 1024 * 1024); // 2MB
        sec.getUserProperties().put("org.apache.tomcat.websocket.binaryBufferSize", 16 * 1024 * 1024); // 2MB
    }
}
