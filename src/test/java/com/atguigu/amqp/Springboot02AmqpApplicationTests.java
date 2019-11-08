package com.atguigu.amqp;

import com.atguigu.amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot02AmqpApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchage() {
        //amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
       // System.out.println("创建完成");
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
    }

    //点对点
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "第一个rabbitmq消息");
        map.put("data", Arrays.asList("helloworld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("java入门到精通", "大忽悠"));
    }

    @Test
    void receive() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    //广播
    @Test
    void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("asdf", "asdf"));
    }
}
