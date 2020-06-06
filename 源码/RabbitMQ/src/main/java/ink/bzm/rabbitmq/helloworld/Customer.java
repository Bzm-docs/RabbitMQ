package ink.bzm.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import ink.bzm.rabbitmq.utils.RabbitMQUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author: buzhengmiao    www.bzm.ink
 * @time: 2020/6/5 9:16
 */
public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
     /*   //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("39.105.164.59");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");

        Connection connection = connectionFactory.newConnection();
*/

        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello",true,false,false,null);


        //消费消息
        //参数1: 消费那个队列的消息 队列名称
        //参数2: 开始消息的自动确认机制
        //参数3: 消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override //最后一个参数: 消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("===================================="+new String(body));
            }
        });
//        channel.close();
//        connection.close();

    }

    }
