package org.example.websocketserver.handler;

import org.example.websocketserver.handler.test.HandlerTestMessage;
import org.example.websocketserver.helper.TransportUtils;
import org.example.websocketserver.messages.BaseMessage;
import org.example.websocketserver.messages.MessageType;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

@Component
public class WebSocketHandlerImpl implements WebSocketHandler {

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            sessions.add(session);
        }
        catch (Exception e) {
            throw new Exception("Error connecting to websocket", e);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("Reached point handle message");

        String payload = (String) message.getPayload();
        logger.info("From client" + payload);

        handleMessageByType(session, payload);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    public void handleMessageByType(WebSocketSession session, String payload) {
        BaseMessage baseMessage = TransportUtils.fromJsonHelper(payload, BaseMessage.class);

        if (baseMessage != null) {
            MessageType messageType = baseMessage.getMessageType();
            switch (messageType) {
                case TEST -> HandlerTestMessage.handleTestMessage(session, payload);
                default -> logger.info("unknown message type received");
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            sessions.remove(session);
        }
        catch (Exception e) {
            throw new Exception("Error after closing connection", e);
        }
    }

    @Override
    public boolean supportsPartialMessages() { return false; }
}
