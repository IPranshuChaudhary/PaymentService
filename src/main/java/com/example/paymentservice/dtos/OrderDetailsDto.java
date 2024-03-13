package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailsDto {

    private Long id;
    private List<String> productIds;
    private int amount;
    private String name;
    private String contact;
    private String email;
}
