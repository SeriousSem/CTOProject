import javax.jms.*;
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
        TruckMenuForm truckMenuForm = new TruckMenuForm();
    }
}

class SendMessage {

    public static String DELIVERY_QUEUE = "deliveryQueue";
    public static String POSITION_QUEUE = "positionQueue";
    public static String EXCEPTION_QUEUE = "exceptionQueue";

    public void produceMessages(String queueName, String message) {
        try {
            Properties ctx = new Properties();
            ctx.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            ctx.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            ctx.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            ctx.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            ctx.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext initialContext = new InitialContext(ctx);
            try {
                System.out.println("op op");
                QueueConnectionFactory qcf = (QueueConnectionFactory) initialContext.lookup("queueFactory");
                Queue q = (Queue) initialContext.lookup(queueName);
                QueueConnection qc = qcf.createQueueConnection();
                QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                QueueSender queueSender = qs.createSender(q);
                TextMessage textMessage = qs.createTextMessage();

                textMessage.setText(message);
                System.out.println("Send: " + message);

                queueSender.send(textMessage);
                System.out.println("Sent");
                qs.close();
                qc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
