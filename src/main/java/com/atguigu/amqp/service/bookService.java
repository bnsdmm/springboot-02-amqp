package com.atguigu.amqp.service;

import com.atguigu.amqp.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class bookService {
    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println("收到消息 : "+book);
    }

}
