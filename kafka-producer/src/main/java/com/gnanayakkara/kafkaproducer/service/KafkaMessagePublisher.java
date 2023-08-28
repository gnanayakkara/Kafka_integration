package com.gnanayakkara.kafkaproducer.service;

import com.gnanayakkara.kafkaproducer.dto.Customer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> resultCompletableFuture = kafkaTemplate.send("TestTopic3", message);
        resultCompletableFuture.whenComplete((result,ex) -> {
            if(ex == null){
                System.out.println("Message sent with offset id : " + result.getRecordMetadata().offset());
            } else {
                System.out.println("Message sending failed with error : " + ex.getMessage());
            }
        });
    }

    public void sendEventsToTopic(Customer customer) {

        try{

            //CompletableFuture<SendResult<String, Object>> resultCompletableFuture = kafkaTemplate.send("DemoTopic", customer);

            CompletableFuture<SendResult<String, Object>> resultCompletableFuture = kafkaTemplate.send(
                    MessageBuilder.withPayload(customer)
                            .setHeader(KafkaHeaders.TOPIC,"DemoTopic")
                            .build());

            resultCompletableFuture.whenComplete((result,ex) -> {
                if(ex == null){
                    System.out.println("Message sent with offset id : " + result.getRecordMetadata().offset());
                } else {
                    System.out.println("Message sending failed with error : " + ex.getMessage());
                }
            });

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
