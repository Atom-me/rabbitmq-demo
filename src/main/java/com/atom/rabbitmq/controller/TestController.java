package com.atom.rabbitmq.controller;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Atom
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public String sendMsg(String msg) {
        Map<String, Object> map5s = new HashMap<>();
        map5s.put("myMsg", msg);

        rabbitTemplate.convertAndSend("normalExchange", "5s", map5s, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                return message;
            }
        });
        System.err.println(msg + "消息发送时间：" + new Date());

//====================================================================================
        Map<String, Object> map10s = new HashMap<>();
        map10s.put("myMsg", msg);

        rabbitTemplate.convertAndSend("normalExchange", "10s", map10s, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });
        System.err.println(msg + "消息发送时间：" + new Date());

//====================================================================================


        Map<String, Object> map30s = new HashMap<>();
        map30s.put("myMsg", msg);

        rabbitTemplate.convertAndSend("normalExchange", "30s", map30s, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("30000");
                return message;
            }
        });
        System.err.println(msg + "消息发送时间：" + new Date());

//====================================================================================


        Map<String, Object> map1min = new HashMap<>();
        map1min.put("myMsg", msg);

        rabbitTemplate.convertAndSend("normalExchange", "1min", map1min, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("60000");
                return message;
            }
        });
        System.err.println(msg + "消息发送时间：" + new Date());


        return msg;
    }
}
