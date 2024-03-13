package com.example.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        if (restTemplate == null){
            return new RestTemplate();
        }

        return restTemplate;
    }
}
