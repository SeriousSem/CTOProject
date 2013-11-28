import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 28.11.13
 * Time: 14:07
 */
public class JMSProducer {
    public static void main(String[] args) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.produceMessages("asd");
    }
}

class SendMessage {
    public void produceMessages(String message) {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {

            final Properties initialContextProperties = new Properties();
            initialContextProperties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            initialContextProperties.put("org.omg.CORBA.ORBInitialPort", "3700");
            initialContextProperties.put("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
            //initialContextProperties.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            //initialContextProperties.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            InitialContext ctx = new InitialContext(initialContextProperties);
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/firstQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/firstQueue");

            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            textMessage = session.createTextMessage();

            textMessage.setText(message);
            System.out.println(message);

            messageProducer.send(textMessage);

            messageProducer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
