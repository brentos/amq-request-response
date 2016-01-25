import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by brentos on 1/21/16.
 */
public class AmqExample {

    public static void main(String args[]) throws Exception {

        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = cf.createConnection("admin","admin");

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test_queue");

        MessageProducer producer = session.createProducer(queue);

        TextMessage message = session.createTextMessage("Hello world");

        producer.send(message);


        MessageConsumer consumer = session.createConsumer(queue);

        TextMessage responseMessage = (TextMessage)consumer.receive(10000);

        System.out.println(responseMessage.getText());

        connection.stop();

        System.exit(0);
    }

}
