package com.example.paymentservice.services;

import com.example.paymentservice.config.RestTemplateConfig;
import com.example.paymentservice.dtos.OrderDetailsDto;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

@Service
public class RazorPayService implements PaymentService{

    private RazorpayClient razorpayClient;
    private RestTemplate restTemplate;

    @Value("${url.to.get.order.details}")
    private String url;

    RazorPayService(RazorpayClient razorpayClient, RestTemplate restTemplate){
        this.razorpayClient = razorpayClient;
        this.restTemplate = restTemplate;
    }

    public OrderDetailsDto getOrderDetails(Long id){

        return restTemplate.getForObject(url+id, OrderDetailsDto.class);
    }

    public String getPaymentLink(Long id) throws RazorpayException {

        OrderDetailsDto orderDetailsDto = getOrderDetails(id);
        String stringId = ""+orderDetailsDto.getId();
        int amount = (int) orderDetailsDto.getAmount();

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + (15 * 60 * 1000));
        paymentLinkRequest.put("reference_id",stringId);
        paymentLinkRequest.put("description","Payment for policy no #23456");
        JSONObject customer = new JSONObject();
        customer.put("contact","+91"+orderDetailsDto.getContact());
        customer.put("name",orderDetailsDto.getName());
        customer.put("email",orderDetailsDto.getEmail());
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
        return paymentLink.get("short_url").toString();
    }
}
