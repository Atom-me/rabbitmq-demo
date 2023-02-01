package com.atom.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author Atom
 */
@RabbitListeners(
        {@RabbitListener(queues = "my-dlx-queue-5-sec", ackMode = "MANUAL"),
                @RabbitListener(queues = "my-dlx-queue-10-sec", ackMode = "MANUAL"),
                @RabbitListener(queues = "my-dlx-queue-30-sec", ackMode = "MANUAL"),
                @RabbitListener(queues = "my-dlx-queue-1-min", ackMode = "MANUAL")}
)
@Component
public class MessageListeners {

    @RabbitHandler
    public void receive(Map<String, Object> map, Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.err.print("====================" + new Date() + "=====");
        System.err.println(map.get("myMsg"));

    }
}
