package sender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MysenderTopic {
    public static void main(String[] args) {

        try{

            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");

            Topic topic = (Topic) applicationContext.getBean("topic");

            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
            TopicConnection connection = factory.createTopicConnection() ;

            // Open a session without transaction and acknowledge automatic
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            // Start the connection
            connection.start();

            // Create a sender
            TopicPublisher sender = session.createPublisher(topic);

            // Create a message
            String message = "yolo2";
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);

            // Send the message
            sender.send(textMessage);
            //sender1.send(textMessage);

            // Close the session
            session.close();

            // Close the connection
            connection.close();

            // TEST
            System.out.println(message);

        }catch(Exception e){
            e.printStackTrace();
        }

    }



}
