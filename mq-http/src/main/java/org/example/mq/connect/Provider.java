package org.example.mq.connect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    private static final Logger logger = LoggerFactory.getLogger(Provider.class);

    private static String EXCHANGE_NAME = "exchange_demo";
    private static String ROUTE_KEY = "routeKey_demo";
    private static String QUEUE_NAME = "queue_demo";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectMQ.getConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTE_KEY);
        String message = "hello world!";
        channel.basicPublish(EXCHANGE_NAME,ROUTE_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        logger.debug("消息生产完毕，内容{}",message);
        channel.close();
        connection.close();
        System.out.println();

    }
}
