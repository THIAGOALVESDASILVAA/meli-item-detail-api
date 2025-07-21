package com.meli.itemdetail.core.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentOptionsTest {

    @Test
    void shouldCreatePaymentOptionsWithDefaultConstructor() {
        PaymentOptions paymentOptions = new PaymentOptions();
        
        assertNull(paymentOptions.getMaxInstallments());
        assertNull(paymentOptions.getInterestFreeInstallments());
        assertNull(paymentOptions.getCreditCards());
        assertNull(paymentOptions.getDebitCards());
        assertNull(paymentOptions.getCashMethods());
        assertNull(paymentOptions.getConditionsByBank());
    }

    @Test
    void shouldCreatePaymentOptionsWithParameterizedConstructor() {
        List<String> creditCards = Arrays.asList("Visa", "Mastercard");
        List<String> debitCards = Arrays.asList("Visa Debit", "Mastercard Debit");
        List<String> cashMethods = Arrays.asList("PIX", "Boleto");
        Map<String, PaymentOptions.BankCondition> conditions = new HashMap<>();
        conditions.put("Banco do Brasil", new PaymentOptions.BankCondition(6, "Special conditions"));
        
        PaymentOptions paymentOptions = new PaymentOptions(
            12, 6, creditCards, debitCards, cashMethods, conditions
        );
        
        assertEquals(12, paymentOptions.getMaxInstallments());
        assertEquals(6, paymentOptions.getInterestFreeInstallments());
        assertEquals(creditCards, paymentOptions.getCreditCards());
        assertEquals(debitCards, paymentOptions.getDebitCards());
        assertEquals(cashMethods, paymentOptions.getCashMethods());
        assertEquals(conditions, paymentOptions.getConditionsByBank());
    }

    @Test
    void shouldSetAndGetAllFields() {
        PaymentOptions paymentOptions = new PaymentOptions();
        List<String> creditCards = Arrays.asList("American Express");
        List<String> debitCards = Arrays.asList("Elo Debit");
        List<String> cashMethods = Arrays.asList("PIX");
        Map<String, PaymentOptions.BankCondition> conditions = new HashMap<>();
        
        paymentOptions.setMaxInstallments(24);
        paymentOptions.setInterestFreeInstallments(12);
        paymentOptions.setCreditCards(creditCards);
        paymentOptions.setDebitCards(debitCards);
        paymentOptions.setCashMethods(cashMethods);
        paymentOptions.setConditionsByBank(conditions);
        
        assertEquals(24, paymentOptions.getMaxInstallments());
        assertEquals(12, paymentOptions.getInterestFreeInstallments());
        assertEquals(creditCards, paymentOptions.getCreditCards());
        assertEquals(debitCards, paymentOptions.getDebitCards());
        assertEquals(cashMethods, paymentOptions.getCashMethods());
        assertEquals(conditions, paymentOptions.getConditionsByBank());
    }

    @Test
    void shouldCreateBankConditionWithDefaultConstructor() {
        PaymentOptions.BankCondition condition = new PaymentOptions.BankCondition();
        
        assertNull(condition.getInterestFreeInstallments());
        assertNull(condition.getDescription());
    }

    @Test
    void shouldCreateBankConditionWithParameterizedConstructor() {
        PaymentOptions.BankCondition condition = new PaymentOptions.BankCondition(10, "Premium account");
        
        assertEquals(10, condition.getInterestFreeInstallments());
        assertEquals("Premium account", condition.getDescription());
    }

    @Test
    void shouldSetAndGetBankConditionFields() {
        PaymentOptions.BankCondition condition = new PaymentOptions.BankCondition();
        
        condition.setInterestFreeInstallments(8);
        condition.setDescription("Regular account");
        
        assertEquals(8, condition.getInterestFreeInstallments());
        assertEquals("Regular account", condition.getDescription());
    }
}
