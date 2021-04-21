package head_office;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receive {

	private final static String queueName = "boo";
	public static String jsonMessage;
	private ConnectionFactory factory;
	private Connection connection;
	private static Channel channel;

	public Receive() throws IOException, TimeoutException {
		factory = new ConnectionFactory();
        factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(queueName, false, false, false, null);
		System.out.println("[*] waiting for messages. To exit press CTRL+C");
	}

	public static String receive() throws Exception {
        DeliverCallback deliverCallBack = (consumerTag,delivery)->{
            String message = new String (delivery.getBody(),"UTF-8");
            System.out.println(" [x] recieved '"+message+"'");
			jsonMessage = message;
        };
		channel.basicConsume(queueName, true, deliverCallBack, consumerTag -> {
		});
		return jsonMessage;
    }
}

