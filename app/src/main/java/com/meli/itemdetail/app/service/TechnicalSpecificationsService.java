package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.TechnicalSpecifications;
import com.meli.itemdetail.core.usecase.GetTechnicalSpecificationsUseCase;
import org.springframework.stereotype.Component;

@Component
public class TechnicalSpecificationsService {

    private final GetTechnicalSpecificationsUseCase getTechnicalSpecificationsUseCase;

    public TechnicalSpecificationsService(GetTechnicalSpecificationsUseCase getTechnicalSpecificationsUseCase) {
        this.getTechnicalSpecificationsUseCase = getTechnicalSpecificationsUseCase;
    }

    public TechnicalSpecifications getTechnicalSpecifications(String productId) {
        return getTechnicalSpecificationsUseCase.execute(productId);
    }
}
