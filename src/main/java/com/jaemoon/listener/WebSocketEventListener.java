package com.jaemoon.listener;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class WebSocketEventListener {

    private final AtomicInteger activeSessions = new AtomicInteger(0);
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        int currentCount = activeSessions.incrementAndGet();
        messagingTemplate.convertAndSend("/topic/userCount", currentCount);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        int currentCount = activeSessions.decrementAndGet();
        messagingTemplate.convertAndSend("/topic/userCount", currentCount);
    }
}
