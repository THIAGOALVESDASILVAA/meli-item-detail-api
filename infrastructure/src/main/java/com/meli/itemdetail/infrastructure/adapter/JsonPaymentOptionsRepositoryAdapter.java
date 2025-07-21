package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.PaymentOptions;
import com.meli.itemdetail.core.domain.port.PaymentOptionsRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonPaymentOptionsRepositoryAdapter implements PaymentOptionsRepositoryPort {

    private final ObjectMapper objectMapper;
    private PaymentOptions cachedOptions;

    public JsonPaymentOptionsRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/payment-options.json");
            var paymentData = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<Map<String, Object>>() {
                    });

            var options = new PaymentOptions();
            options.setMaxInstallments((Integer) paymentData.get("max_installments"));
            options.setInterestFreeInstallments((Integer) paymentData.get("interest_free_installments"));

            options.setCreditCards(getStringList(paymentData, "credit_cards"));
            options.setDebitCards(getStringList(paymentData, "debit_cards"));
            options.setCashMethods(getStringList(paymentData, "cash_methods"));

            var banksData = (Map<String, Map<String, Object>>) paymentData.get("conditions_by_bank");
            if (banksData != null) {
                var bankConditions = banksData.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> mapToBankCondition(entry.getValue()),
                                (v1, v2) -> v1,
                                ConcurrentHashMap::new));
                options.setConditionsByBank(bankConditions);
            }

            this.cachedOptions = options;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load payment options data", e);
        }
    }

    @Override
    public PaymentOptions findAll() {
        return cachedOptions;
    }

    @SuppressWarnings("unchecked")
    private List<String> getStringList(Map<String, Object> data, String key) {
        var list = (List<String>) data.get(key);
        return list != null ? list : Collections.emptyList();
    }

    private PaymentOptions.BankCondition mapToBankCondition(Map<String, Object> data) {
        return new PaymentOptions.BankCondition(
                (Integer) data.get("interest_free_installments"),
                (String) data.get("description"));
    }
}
