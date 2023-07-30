package com.company.factory;

import com.company.model.Message;
import lombok.Data;

public class MessageFactory {

    private int nextMessageIndex = 1;

    public Message create(){
        return new Message(String.format("Message#%d", this.nextMessageIndex++));
    }

}
