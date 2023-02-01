//package com.atom.rabbitmq.listener;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.Map;
//
///**
// * @author Atom
// */
//@RabbitListener(queues = "my-dlx-queue")
//@Component
//public class MessageListener {
//
//    @RabbitHandler
//    public void receive(Map<String, Object> map) {
//        System.err.print("====================" + new Date() + "=====");
//        System.err.println(map.get("myMsg"));
//    }
//}
