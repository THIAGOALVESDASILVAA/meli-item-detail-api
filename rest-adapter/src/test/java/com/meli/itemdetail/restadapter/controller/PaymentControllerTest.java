package com.meli.itemdetail.restadapter.controller;

import com.meli.itemdetail.app.service.PaymentOptionsService;
import com.meli.itemdetail.core.domain.model.PaymentOptions;
import com.meli.itemdetail.restadapter.RestAdapterApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@ContextConfiguration(classes = RestAdapterApplication.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentOptionsService paymentOptionsService;

    @Test
    void shouldReturnPaymentOptionsForProduct() throws Exception {
        String productId = "MLB123456789";
        PaymentOptions paymentOptions = createPaymentOptions();
        
        when(paymentOptionsService.getPaymentOptions()).thenReturn(paymentOptions);
        
        mockMvc.perform(get("/products/{productId}/payment-options", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.maxInstallments").value(12))
                .andExpect(jsonPath("$.interestFreeInstallments").value(6))
                .andExpect(jsonPath("$.creditCards[0]").value("Visa"));
    }

    @Test
    void shouldReturnPaymentOptionsWithCashMethods() throws Exception {
        String productId = "MLB987654321";
        PaymentOptions paymentOptions = createPaymentOptionsWithCash();
        
        when(paymentOptionsService.getPaymentOptions()).thenReturn(paymentOptions);
        
        mockMvc.perform(get("/products/{productId}/payment-options", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.cashMethods").isArray())
                .andExpect(jsonPath("$.cashMethods.length()").value(2))
                .andExpect(jsonPath("$.debitCards[0]").value("Mastercard"));
    }

    private PaymentOptions createPaymentOptions() {
        PaymentOptions options = new PaymentOptions();
        options.setMaxInstallments(12);
        options.setInterestFreeInstallments(6);
        options.setCreditCards(Arrays.asList("Visa", "Mastercard", "American Express"));
        options.setDebitCards(Arrays.asList("Visa", "Mastercard"));
        options.setCashMethods(Arrays.asList("PIX"));
        
        Map<String, PaymentOptions.BankCondition> conditions = new HashMap<>();
        PaymentOptions.BankCondition bankCondition = new PaymentOptions.BankCondition(6, "6x sem juros");
        conditions.put("Itaú", bankCondition);
        options.setConditionsByBank(conditions);
        
        return options;
    }

    private PaymentOptions createPaymentOptionsWithCash() {
        PaymentOptions options = new PaymentOptions();
        options.setMaxInstallments(10);
        options.setInterestFreeInstallments(3);
        options.setCreditCards(Arrays.asList("Visa", "Mastercard"));
        options.setDebitCards(Arrays.asList("Mastercard", "Elo"));
        options.setCashMethods(Arrays.asList("PIX", "Boleto"));
        
        return options;
    }
}
