package org.example.websocketserver.helper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

public class TransportUtils {
    public static Logger logger = Logger.getLogger(TransportUtils.class.getName());
    private static final Gson gson = new Gson();

    private static final int timelimit = 7000; //7sec
    private static final int buffersize = 256 * 1024; // 256kb

    public static void checkSessionAndPayload(WebSocketSession session, String payload) {
        if (session == null) {
            throw new NullPointerException("session is null");
        }
        if ( payload == null) {
            throw new NullPointerException("payload is null");
        }
    }

    public static <T> T fromJsonHelper(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        }
        catch (JsonParseException e) {
            throw new JsonParseException("Json parse error: ", e);
        }
    }

    public static String toJsonHelper(Object obj) {
        return gson.toJson(obj);
    }

    private static WebSocketSession syncSend(WebSocketSession session) {
        return new ConcurrentWebSocketSessionDecorator(session, timelimit, buffersize);
    }

    public static void msgSend(WebSocketSession session, String responsePayload) {
        checkSessionAndPayload(session, responsePayload);
        try {
            WebSocketSession sync = syncSend(session);
            sync.sendMessage(new TextMessage(responsePayload));
        } catch (IOException e) {
            throw new RuntimeException("Error send process: ", e);
        }
    }

    public static void broadcastMsg(String responsePayload, WebSocketSession sender, List<WebSocketSession> sessions) {
        if(sessions == null) {
            logger.warning("Error: List is null");
            return;
        }

        for(WebSocketSession client : sessions) {
            if(client.isOpen()) {
                msgSend(client, responsePayload);
            }
            else {
                logger.info("Client is not open");
            }
        }
    }

    public static <T> void nullCheck (T msgObject) {
        if (msgObject == null) {
            throw new NullPointerException("msgObject is null");
        }
    }

}
