package org.example.websocketserver.handler;

import org.example.websocketserver.handler.test.HandlerTestMessage;
import org.example.websocketserver.helper.TransportUtils;
import org.example.websocketserver.messages.HobbyMessage;
import org.example.websocketserver.messages.MessageType;
import org.example.websocketserver.messages.TestMessage;
import org.example.websocketserver.model.Hobby;
import org.example.websocketserver.repository.InMemoryHobbyRepo;
import org.springframework.web.socket.WebSocketSession;

import java.util.logging.Logger;

public class HandlerHobbyMessage {
    private final static Logger logger = Logger.getLogger(HandlerTestMessage.class.getName());

    public static void handleHobbyMessage(WebSocketSession session, String payload, InMemoryHobbyRepo hobbyRepo) {
        TransportUtils.checkSessionAndPayload(session, payload);

        HobbyMessage hobbyMessage = TransportUtils.fromJsonHelper(payload, HobbyMessage.class);

        TransportUtils.nullCheck(hobbyMessage);
        logger.info("Test-Message received: Add " + hobbyMessage.getTitle() + " to repo");

        int id = hobbyMessage.getId();
        String title = hobbyMessage.getTitle();
        int number = hobbyMessage.getNumber();

        Hobby hobby = new Hobby(id, title, number);

        if(hobby != null) {
            hobbyRepo.addHobby(hobby);
        }
    }
}
