package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.TechnicalSpecifications;
import com.meli.itemdetail.core.domain.port.TechnicalSpecificationsRepositoryPort;

public class GetTechnicalSpecificationsUseCase {

    private final TechnicalSpecificationsRepositoryPort technicalSpecificationsRepositoryPort;

    public GetTechnicalSpecificationsUseCase(
            TechnicalSpecificationsRepositoryPort technicalSpecificationsRepositoryPort) {
        this.technicalSpecificationsRepositoryPort = technicalSpecificationsRepositoryPort;
    }

    public TechnicalSpecifications execute(String productId) {
        return technicalSpecificationsRepositoryPort.findByProductId(productId);
    }
}
