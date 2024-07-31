package org.example.websocketserver.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HobbyMessage extends BaseMessage {
    private int id;
    private String title;
    private int number;

    public HobbyMessage(){
        this.messageType = MessageType.HOBBY;
    }
}
