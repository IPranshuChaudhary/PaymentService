package com.example.paymentservice.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {

    private RazorpayClient razorpayClient;

    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        if (razorpayClient == null){
            return new RazorpayClient("rzp_test_Nw07ZBP7MhQ54v","EXPjEkBhK58V0EZLmTOAd0hU");
        }
        return razorpayClient;
    }
}
