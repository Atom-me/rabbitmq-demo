package com.atom.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Atom
 */
@Configuration
public class RabbitConfig {



    // 公共普通交换机
    @Bean("normalExchange")
    public Exchange normalExchange(){
        return ExchangeBuilder.directExchange("normalExchange").durable(true)
                .build();
    }

    //region <5s延时队列配置>

    // 定义5s延时队列,即普通队列，消息根据路由键 "5s "路由到这个队列，没有消费者监听此队列
    @Bean("delayQueueFiveSec")
    public Queue delayQueueFiveSec() {
        return QueueBuilder.durable("delayQueue_5s")
                // 定义队列的TTL 为 5s
//                .withArgument("x-expires", 5000)
                // 如果消息过期，则会被投递到 my-dlx-exchange 交换机
                .withArgument("x-dead-letter-exchange", "my-dlx-exchange")
                // 如果消息过期， my-dlx-exchange 交换机会根据  x-dead-letter-routing-key 投递消息到对应的死信队列
                .withArgument("x-dead-letter-routing-key", "delay_5s")
                .build();
    }

    // 定义死信交换机（共用一个死信交换机）
    @Bean("dlxExchange")
    public Exchange dlxExchange() {
        return ExchangeBuilder.directExchange("my-dlx-exchange").build();
    }

    //定义5s过期之后的死信队列
    @Bean("dlxQueueFiveSec")
    public Queue dlxQueueFiveSec() {
        return QueueBuilder.durable("my-dlx-queue-5-sec").build();
    }

    //绑定5s死信队列与共用的死信交换机
    @Bean("dlxBinding5s")
    public Binding dlxBinding5s(@Qualifier("dlxQueueFiveSec") Queue queue, @Qualifier("dlxExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("delay_5s").noargs();
    }

    // 绑定普通交换机与延时队列
    @Bean("normalXBinding5s")
    public Binding normalXBinding5s(@Qualifier("delayQueueFiveSec") Queue queue, @Qualifier("normalExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("5s").noargs();
    }



    //endregion



    //region <10s延时队列配置>

    // 定义10s延时队列,即普通队列，消息根据路由键 "10s "路由到这个队列，没有消费者监听此队列
    @Bean("delayQueueTenSec")
    public Queue delayQueueTenSec() {
        return QueueBuilder.durable("delayQueue_10s")
                // 定义队列的TTL 为 10s
//                .withArgument("x-expires", 10000)
                // 如果消息过期，则会被投递到 my-dlx-exchange 交换机
                .withArgument("x-dead-letter-exchange", "my-dlx-exchange")
                // 如果消息过期， my-dlx-exchange 交换机会根据  x-dead-letter-routing-key 投递消息到对应的队列
                .withArgument("x-dead-letter-routing-key", "delay_10s")
                .build();
    }

    //定义10s过期之后的死信队列
    @Bean("dlxQueueTenSec")
    public Queue dlxQueueTenSec() {
        return QueueBuilder.durable("my-dlx-queue-10-sec").build();
    }



    //绑定死信队列与交换机
    @Bean("dlxBinding10s")
    public Binding dlxBinding10s(@Qualifier("dlxExchange") Exchange exchange, @Qualifier("dlxQueueTenSec") Queue queue) {
        return BindingBuilder
                .bind(queue).to(exchange).with("delay_10s").noargs();
    }


    @Bean("normalXBinding10s")
    public Binding normalXBinding10s(@Qualifier("delayQueueTenSec") Queue queue, @Qualifier("normalExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("10s").noargs();
    }

    //endregion

    //region <30s延时队列配置>

    // 定义30s延时队列,即普通队列，消息根据路由键 "30s "路由到这个队列，没有消费者监听此队列
    @Bean("delayQueueThirtySec")
    public Queue delayQueueThirtySec() {
        return QueueBuilder.durable("delayQueue_30s")
                // 定义队列的TTL 为 30s
//                .withArgument("x-expires", 30000)
                // 如果消息过期，则会被投递到 my-dlx-exchange 交换机
                .withArgument("x-dead-letter-exchange", "my-dlx-exchange")
                // 如果消息过期， my-dlx-exchange 交换机会根据  x-dead-letter-routing-key 投递消息到对应的队列
                .withArgument("x-dead-letter-routing-key", "delay_30s")
                .build();
    }


    //定义死信队列
    @Bean("dlxQueueThirtySec")
    public Queue dlxQueueThirtySec() {
        return QueueBuilder.durable("my-dlx-queue-30-sec").build();
    }

    //绑定死信队列与交换机
    @Bean("dlxBinding30s")
    public Binding dlxBinding30s(@Qualifier("dlxExchange") Exchange exchange, @Qualifier("dlxQueueThirtySec") Queue queue) {
        return BindingBuilder
                .bind(queue).to(exchange).with("delay_30s").noargs();
    }


    @Bean("normalXBinding30s")
    public Binding normalXBinding30s(@Qualifier("delayQueueThirtySec") Queue queue, @Qualifier("normalExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("30s").noargs();
    }

    //endregion



    //region <1min延时队列配置>
    // 定义1min延时队列,即普通队列，消息根据路由键 "1min "路由到这个队列，没有消费者监听此队列
    @Bean("delayQueueOneMin")
    public Queue delayQueueOneMin() {
        return QueueBuilder.durable("delayQueue_1min")
//                .withArgument("x-expires", 60000)
                // 如果消息过期，则会被投递到 my-dlx-exchange 交换机
                .withArgument("x-dead-letter-exchange", "my-dlx-exchange")
                // 如果消息过期， my-dlx-exchange 交换机会根据  x-dead-letter-routing-key 投递消息到对应的队列
                .withArgument("x-dead-letter-routing-key", "delay_1min")
                .build();
    }

    //定义死信队列
    @Bean("dlxQueueOneMin")
    public Queue dlxQueueOneMin() {
        return QueueBuilder.durable("my-dlx-queue-1-min").build();
    }


    //绑定死信队列与交换机
    @Bean("dlxBinding1min")
    public Binding dlxBinding1min(@Qualifier("dlxExchange") Exchange exchange, @Qualifier("dlxQueueOneMin") Queue queue) {
        return BindingBuilder
                .bind(queue).to(exchange).with("delay_1min").noargs();
    }

    @Bean("normalXBinding1min")
    public Binding normalXBinding1min(@Qualifier("delayQueueOneMin") Queue queue, @Qualifier("normalExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("1min").noargs();
    }

    //endregion








}
