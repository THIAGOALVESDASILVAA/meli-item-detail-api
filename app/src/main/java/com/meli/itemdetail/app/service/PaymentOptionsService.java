package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.PaymentOptions;
import com.meli.itemdetail.core.usecase.GetPaymentOptionsUseCase;
import org.springframework.stereotype.Component;

@Component
public class PaymentOptionsService {

    private final GetPaymentOptionsUseCase getPaymentOptionsUseCase;

    public PaymentOptionsService(GetPaymentOptionsUseCase getPaymentOptionsUseCase) {
        this.getPaymentOptionsUseCase = getPaymentOptionsUseCase;
    }

    public PaymentOptions getPaymentOptions() {
        return getPaymentOptionsUseCase.execute();
    }
}
