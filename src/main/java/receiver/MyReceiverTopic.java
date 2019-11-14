package receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyReceiverTopic {
    public static void main(String[] args) {
        try{

            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");

            Topic topic = (Topic) applicationContext.getBean("topic");

            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
            TopicConnection connection = factory.createTopicConnection() ;

            // Open a session
            TopicSession session = connection.createTopicSession(true,0);

            // start the connection
            connection.start();

            // Create a receive
            TopicSubscriber receiver = session.createSubscriber(topic);

            // Receive the message
            Message m = receiver.receive();

            // TEST
            System.out.println(m + "hello");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

/*
Is a topic able to receive message from many publishers? Yes
Is a queue able to send message to many subscribers? No
 */