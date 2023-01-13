package org.example.mq.connect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectMQ {

    private static String EXCHANGE_NAME = "exchange_demo";
    private static String ROUTE_KEY = "routeKey_demo";
    private static String QUEUE_NAME = "queue_demo";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.60.163.158");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/dudu");
        Connection connection = factory.newConnection();
        return connection;
    }
}
