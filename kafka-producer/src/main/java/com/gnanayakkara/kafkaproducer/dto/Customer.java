package com.gnanayakkara.kafkaproducer.dto;

import lombok.Data;

@Data
public class Customer {

    private Integer id;
    private String name;
    private String email;
    private String contactNo;

}
