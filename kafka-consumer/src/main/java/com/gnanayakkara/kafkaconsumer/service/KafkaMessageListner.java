package com.gnanayakkara.kafkaconsumer.service;

import com.gnanayakkara.kafkaconsumer.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListner {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListner.class);

    @KafkaListener(topics = "DemoTopic",groupId = "DemoGroup",containerFactory = "kafkaListnerContainer")
    public void consumeEvent(Customer customer){
        logger.info("Consumer consumed the message {}" ,customer.toString());
    }

    /*@KafkaListener(topics = "TestTopic3",groupId = "adjustment-1")
    public void consume1(String message){
        logger.info("Consumer 1 consumed the message {}" ,message);
    }

    @KafkaListener(topics = "TestTopic3",groupId = "adjustment-1")
    public void consume2(String message){
        logger.info("Consumer 2 consumed the message {}" ,message);
    }

    @KafkaListener(topics = "TestTopic3",groupId = "adjustment-1")
    public void consume3(String message){
        logger.info("Consumer 3 consumed the message {}" ,message);
    }

    @KafkaListener(topics = "TestTopic3",groupId = "adjustment-1")
    public void consume4(String message){
        logger.info("Consumer 4 consumed the message {}" ,message);
    }

    @KafkaListener(topics = "TestTopic3",groupId = "adjustment-1")
    public void consume5(String message){
        logger.info("Consumer 5 consumed the message {}" ,message);
    }

    @KafkaListener(topics = "TestTopic3",groupId = "adjustment-1")
    public void consume6(String message){
        logger.info("Consumer 6 consumed the message {}" ,message);
    }*/
}
