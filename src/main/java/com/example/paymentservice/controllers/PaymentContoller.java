package com.example.paymentservice.controllers;

import com.example.paymentservice.services.RazorPayService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentContoller {

    private RazorPayService paymentsService;

    PaymentContoller(RazorPayService paymentsService){
        this.paymentsService = paymentsService;
    }

    @GetMapping("/{id}")
    public String getPaymentLink(@PathVariable Long id) throws RazorpayException {
        return paymentsService.getPaymentLink(id);
    }

    public void handleWebHookEvent(){
//        return null;
    }
}
