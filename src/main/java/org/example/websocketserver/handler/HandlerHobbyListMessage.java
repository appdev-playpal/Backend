package org.example.websocketserver.handler;

import org.example.websocketserver.handler.test.HandlerTestMessage;
import org.example.websocketserver.helper.TransportUtils;
import org.example.websocketserver.messages.HobbyListMessage;
import org.example.websocketserver.messages.HobbyMessage;
import org.example.websocketserver.model.Hobby;
import org.example.websocketserver.repository.InMemoryHobbyRepo;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class HandlerHobbyListMessage {
    private final static Logger logger = Logger.getLogger(HandlerTestMessage.class.getName());

    public static void handleHobbyListMessage(WebSocketSession session, String payload, InMemoryHobbyRepo hobbyRepo) {
        TransportUtils.checkSessionAndPayload(session, payload);

        HobbyListMessage hobbyListMessage = TransportUtils.fromJsonHelper(payload, HobbyListMessage.class);

        TransportUtils.nullCheck(hobbyListMessage);

        Set<Hobby> hobbies = hobbyRepo.getAllHobbies();
        hobbyListMessage.setHobbies(hobbies);
        String response = TransportUtils.toJsonHelper(hobbyListMessage);
        logger.info("Hobby-List send: " + hobbies.size());
        TransportUtils.msgSend(session, response);
    }
}
