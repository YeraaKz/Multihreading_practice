package com.company.consumer;

import com.company.broker.MessageBroker;
import com.company.model.Message;

import java.util.concurrent.TimeUnit;

public class MessageConsumingTask implements Runnable{

    private final MessageBroker messageBroker;

    public MessageConsumingTask(MessageBroker messageBroker){
        this.messageBroker = messageBroker;
    }


    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                TimeUnit.SECONDS.sleep(1);
                Message message = this.messageBroker.consume();
                System.out.printf("Message: %s was consumed\n", message.getData());
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }


}
