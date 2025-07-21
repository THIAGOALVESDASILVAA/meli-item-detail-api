package com.meli.itemdetail.restadapter.controller;

import com.meli.itemdetail.app.service.PaymentOptionsService;
import com.meli.itemdetail.core.domain.model.PaymentOptions;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class PaymentController {

    private final PaymentOptionsService paymentOptionsService;

    public PaymentController(PaymentOptionsService paymentOptionsService) {
        this.paymentOptionsService = paymentOptionsService;
    }

    @GetMapping("/{productId}/payment-options")
    public ResponseEntity<PaymentOptions> getPaymentOptions(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(paymentOptionsService.getPaymentOptions());
    }

    private void setMDCContext(String productId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("productId", productId);
    }
}
