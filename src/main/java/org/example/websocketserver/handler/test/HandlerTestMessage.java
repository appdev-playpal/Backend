package org.example.websocketserver.handler.test;

import org.example.websocketserver.helper.TransportUtils;
import org.example.websocketserver.messages.MessageType;
import org.example.websocketserver.messages.TestMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.logging.Logger;

public class HandlerTestMessage {
    private final static Logger logger = Logger.getLogger(HandlerTestMessage.class.getName());

    public static void handleTestMessage(WebSocketSession session, String payload){
        TransportUtils.checkSessionAndPayload(session, payload);

        TestMessage testMessage = TransportUtils.fromJsonHelper(payload, TestMessage.class);

        TransportUtils.nullCheck(testMessage);
        logger.info("Test-Message received " + testMessage.getText());

        testMessage.setMessageType(MessageType.TEST);
        testMessage.setText("Test");

        String response = TransportUtils.toJsonHelper(testMessage);
        logger.info("Test-Message changed and send: " + testMessage.getText());
        TransportUtils.msgSend(session, response);
    }
}
