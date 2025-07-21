package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.PaymentOptions;
import com.meli.itemdetail.core.domain.port.PaymentOptionsRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPaymentOptionsUseCaseTest {

    @Mock
    private PaymentOptionsRepositoryPort repository;

    private GetPaymentOptionsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetPaymentOptionsUseCase(repository);
    }

    @Test
    void shouldReturnPaymentOptions() {
        PaymentOptions expectedOptions = createPaymentOptions();
        when(repository.findAll()).thenReturn(expectedOptions);

        PaymentOptions result = useCase.execute();

        assertNotNull(result);
        assertEquals(12, result.getMaxInstallments());
        assertEquals(6, result.getInterestFreeInstallments());
        assertEquals(Arrays.asList("Visa", "Mastercard", "American Express"), result.getCreditCards());
        verify(repository).findAll();
    }

    private PaymentOptions createPaymentOptions() {
        PaymentOptions options = new PaymentOptions();
        options.setMaxInstallments(12);
        options.setInterestFreeInstallments(6);
        options.setCreditCards(Arrays.asList("Visa", "Mastercard", "American Express"));
        options.setDebitCards(Arrays.asList("Visa", "Mastercard"));
        options.setCashMethods(Arrays.asList("Pix", "Boleto"));
        
        Map<String, PaymentOptions.BankCondition> conditions = new HashMap<>();
        conditions.put("Itau", new PaymentOptions.BankCondition(3, "3x sem juros"));
        options.setConditionsByBank(conditions);
        
        return options;
    }
}
