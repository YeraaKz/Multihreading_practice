package com.company.broker;

import com.company.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.Queue;

@Data
public class MessageBroker {

    private final Queue<Message> messagesToBeConsumed;

    private final int maxMessagesCapacity;

    public MessageBroker(int maxMessagesCapacity){
        this.messagesToBeConsumed = new ArrayDeque<>(maxMessagesCapacity);
        this.maxMessagesCapacity = maxMessagesCapacity;
    }

    /*
    * Method for producing messages in thread
    * */
    public synchronized void produce(final Message message){
        try {
            while (messagesToBeConsumed.size() >= maxMessagesCapacity) {
                super.wait();
            }

            this.messagesToBeConsumed.add(message);
            super.notifyAll();
        }
        catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /*
    * Method to consume the message
    * */
    public synchronized Message consume(){
        try{
            while (messagesToBeConsumed.isEmpty()){
                super.wait();
            }
            final Message message = this.messagesToBeConsumed.poll();
            super.notifyAll();
            return message;
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
