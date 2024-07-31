package org.example.websocketserver.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.websocketserver.model.Hobby;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class HobbyListMessage extends BaseMessage {
    private Set<Hobby> hobbies;

    public HobbyListMessage() {
        this.messageType = MessageType.HOBBYLIST;
    }
}
