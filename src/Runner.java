import com.company.broker.MessageBroker;
import com.company.consumer.MessageConsumingTask;
import com.company.producer.MessageProducingTask;

public class Runner {
    public static void main(String[] args) {

        int brokerMaxCapacity = 5;

        MessageBroker messageBroker = new MessageBroker(brokerMaxCapacity);

        Thread producingThread = new Thread(new MessageProducingTask(messageBroker));
        Thread consumingThread = new Thread(new MessageConsumingTask(messageBroker));

        producingThread.start();
        consumingThread.start();
    }
}
