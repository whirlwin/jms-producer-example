package no.whirlwin.jms.producer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 
 * @author whirlwin
 *
 */
public class JMSProducer {

    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    //Configuration
    public JMSProducer() throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        QueueConnectionFactory queueConnectionFactory =
                (QueueConnectionFactory) initialContext
                .lookup("jms/QueueConnectionFactory");
        connection = queueConnectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        messageProducer = session.createProducer((Destination) initialContext
                .lookup("jms/JMSConsumer"));
    }

    private void sendMessage(String message) {
        try {
            messageProducer.send(session.createTextMessage(message));
        } catch (JMSException jmsException) {
            jmsException.printStackTrace();
        }
    }

    private void cleanup() {
        try {
            session.close();
            connection.close();
        } catch (JMSException jmsException) {
            jmsException.printStackTrace();
        }
    }

    public static void main(String[] messages) {
        try {
            JMSProducer jmsProducer = new JMSProducer();
            
            // Check for command line arguments
            if (messages.length > 0) {
                for (String message : messages) {
                    jmsProducer.sendMessage(message);
                }
            } else {
                jmsProducer.sendMessage("---------------------------> foobar");
            }

            jmsProducer.cleanup();
            
        } catch (NamingException namingException) {
            namingException.printStackTrace();
        } catch (JMSException jmsException) {
            jmsException.printStackTrace();
        }
    }
}