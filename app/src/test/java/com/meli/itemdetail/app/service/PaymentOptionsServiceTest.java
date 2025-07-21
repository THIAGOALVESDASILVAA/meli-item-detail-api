package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.PaymentOptions;
import com.meli.itemdetail.core.usecase.GetPaymentOptionsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentOptionsServiceTest {

    @Mock
    private GetPaymentOptionsUseCase getPaymentOptionsUseCase;

    private PaymentOptionsService paymentOptionsService;

    @BeforeEach
    void setUp() {
        paymentOptionsService = new PaymentOptionsService(getPaymentOptionsUseCase);
    }

    @Test
    void shouldReturnPaymentOptionsWhenUseCaseReturnsOptions() {
        PaymentOptions expectedOptions = createPaymentOptions();
        
        when(getPaymentOptionsUseCase.execute()).thenReturn(expectedOptions);
        
        PaymentOptions result = paymentOptionsService.getPaymentOptions();
        
        assertEquals(expectedOptions, result);
        assertEquals(12, result.getMaxInstallments());
        assertEquals(6, result.getInterestFreeInstallments());
        verify(getPaymentOptionsUseCase).execute();
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        when(getPaymentOptionsUseCase.execute()).thenReturn(null);
        
        PaymentOptions result = paymentOptionsService.getPaymentOptions();
        
        assertNull(result);
        verify(getPaymentOptionsUseCase).execute();
    }

    @Test
    void shouldDelegateToUseCase() {
        when(getPaymentOptionsUseCase.execute()).thenReturn(createPaymentOptions());
        
        paymentOptionsService.getPaymentOptions();
        
        verify(getPaymentOptionsUseCase).execute();
    }

    private PaymentOptions createPaymentOptions() {
        PaymentOptions options = new PaymentOptions();
        options.setMaxInstallments(12);
        options.setInterestFreeInstallments(6);
        options.setCreditCards(Arrays.asList("Visa", "Mastercard", "American Express"));
        return options;
    }
}
