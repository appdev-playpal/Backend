package org.example.websocketserver.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestMessage extends BaseMessage{
    private String text;
    private String messageIdentifier;

    public TestMessage(){
        this.messageType = MessageType.TEST;
    }
}
