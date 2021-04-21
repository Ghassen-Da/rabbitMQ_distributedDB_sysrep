package branch_office;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String queueName = "bao";
	private ConnectionFactory factory;
	private Channel channel;
	private Connection connection;

	public Send() {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			this.connection = factory.newConnection();
			this.channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);
			System.out.println(channel);
		}
		catch (Exception e) {
			e.printStackTrace();
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
