package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.TechnicalSpecifications;
import com.meli.itemdetail.core.usecase.GetTechnicalSpecificationsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnicalSpecificationsServiceTest {

    @Mock
    private GetTechnicalSpecificationsUseCase getTechnicalSpecificationsUseCase;

    private TechnicalSpecificationsService technicalSpecificationsService;

    @BeforeEach
    void setUp() {
        technicalSpecificationsService = new TechnicalSpecificationsService(getTechnicalSpecificationsUseCase);
    }

    @Test
    void shouldReturnTechnicalSpecificationsWhenUseCaseReturnsSpecifications() {
        String productId = "MLB123456789";
        TechnicalSpecifications expectedSpecs = createTechnicalSpecifications();
        
        when(getTechnicalSpecificationsUseCase.execute(productId)).thenReturn(expectedSpecs);
        
        TechnicalSpecifications result = technicalSpecificationsService.getTechnicalSpecifications(productId);
        
        assertEquals(expectedSpecs, result);
        assertEquals("6.1 polegadas Super Retina XDR", result.getScreen());
        assertEquals("128GB", result.getInternalMemory());
        verify(getTechnicalSpecificationsUseCase).execute(productId);
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        String productId = "MLB999999999";
        
        when(getTechnicalSpecificationsUseCase.execute(productId)).thenReturn(null);
        
        TechnicalSpecifications result = technicalSpecificationsService.getTechnicalSpecifications(productId);
        
        assertNull(result);
        verify(getTechnicalSpecificationsUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getTechnicalSpecificationsUseCase.execute(productId)).thenReturn(createTechnicalSpecifications());
        
        technicalSpecificationsService.getTechnicalSpecifications(productId);
        
        verify(getTechnicalSpecificationsUseCase).execute(productId);
    }

    private TechnicalSpecifications createTechnicalSpecifications() {
        TechnicalSpecifications specs = new TechnicalSpecifications();
        specs.setScreen("6.1 polegadas Super Retina XDR");
        specs.setInternalMemory("128GB");
        specs.setRamMemory("6GB");
        specs.setRearCamera("48MP + 12MP");
        specs.setFrontCamera("12MP");
        specs.setUnlockMethod("Face ID");
        specs.setNfc(true);
        return specs;
    }
}
