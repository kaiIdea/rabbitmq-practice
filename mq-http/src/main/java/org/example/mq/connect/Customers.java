package org.example.mq.connect;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Customers {
    private static final Logger logger = LoggerFactory.getLogger(Customers.class);

    private static String QUEUE_NAME = "queue_demo";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectMQ.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(64);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                logger.info("收到消息：{}", new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME,consumer);
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();

    }
}
