package branch_office;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String queueName = "boo";
	private ConnectionFactory factory;
	private Channel channel;

	public Send() throws IOException, TimeoutException {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel();) {
			channel.queueDeclare(queueName, false, false, false, null);
		}
	}

	public void send(String msg) throws Exception {
		try {
			channel.basicPublish("", queueName, null, msg.getBytes());
			System.out.println("[x] sent '" + msg + "'");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	}
