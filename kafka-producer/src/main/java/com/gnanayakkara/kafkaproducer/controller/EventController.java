package com.gnanayakkara.kafkaproducer.controller;

import com.gnanayakkara.kafkaproducer.dto.Customer;
import com.gnanayakkara.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    private KafkaMessagePublisher kafkaMessagePublisher;

    EventController(KafkaMessagePublisher kafkaMessagePublisher) {
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try {
            for(int i = 0; i < 10000; i++){
                kafkaMessagePublisher.sendMessageToTopic(message + " : " + i);
            }

            return ResponseEntity.ok("Message published successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendEvents(@RequestBody Customer customer){
        kafkaMessagePublisher.sendEventsToTopic(customer);
        return ResponseEntity.ok("Message published successfully");
    }

}
