package com.company.producer;

import com.company.broker.MessageBroker;
import com.company.factory.MessageFactory;
import com.company.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MessageProducingTask implements Runnable {

    private final MessageBroker messageBroker;

    private final MessageFactory messageFactory;

    public MessageProducingTask(MessageBroker messageBroker){
        this.messageBroker = messageBroker;
        messageFactory = new MessageFactory();
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()){
                Message message = messageFactory.create();

                TimeUnit.SECONDS.sleep(1);

                messageBroker.produce(message);
                System.out.printf("Message '%s' is produced\n", message);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }

}
