package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.PaymentOptions;
import com.meli.itemdetail.core.domain.port.PaymentOptionsRepositoryPort;

public class GetPaymentOptionsUseCase {

    private final PaymentOptionsRepositoryPort paymentOptionsRepositoryPort;

    public GetPaymentOptionsUseCase(PaymentOptionsRepositoryPort paymentOptionsRepositoryPort) {
        this.paymentOptionsRepositoryPort = paymentOptionsRepositoryPort;
    }

    public PaymentOptions execute() {
        return paymentOptionsRepositoryPort.findAll();
    }
}
