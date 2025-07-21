package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.PaymentOptions;

public interface PaymentOptionsRepositoryPort {
    PaymentOptions findAll();
}
