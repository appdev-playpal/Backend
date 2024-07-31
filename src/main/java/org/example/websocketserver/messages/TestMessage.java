package org.example.websocketserver.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestMessage extends BaseMessage{
    private String text;

    public TestMessage(){
        this.messageType = MessageType.TEST;
    }
}
