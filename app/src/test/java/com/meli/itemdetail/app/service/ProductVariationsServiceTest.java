package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductVariation;
import com.meli.itemdetail.core.usecase.GetProductVariationsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductVariationsServiceTest {

    @Mock
    private GetProductVariationsUseCase getProductVariationsUseCase;

    private ProductVariationsService productVariationsService;

    @BeforeEach
    void setUp() {
        productVariationsService = new ProductVariationsService(getProductVariationsUseCase);
    }

    @Test
    void shouldReturnProductVariationsWhenUseCaseReturnsVariations() {
        String productId = "MLB123456789";
        List<ProductVariation> expectedVariations = Arrays.asList(createProductVariation());
        
        when(getProductVariationsUseCase.execute(productId)).thenReturn(expectedVariations);
        
        List<ProductVariation> result = productVariationsService.getProductVariations(productId);
        
        assertEquals(expectedVariations, result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getAvailableColors().size());
        verify(getProductVariationsUseCase).execute(productId);
    }

    @Test
    void shouldReturnEmptyListWhenUseCaseReturnsEmptyList() {
        String productId = "MLB999999999";
        
        when(getProductVariationsUseCase.execute(productId)).thenReturn(Collections.emptyList());
        
        List<ProductVariation> result = productVariationsService.getProductVariations(productId);
        
        assertTrue(result.isEmpty());
        verify(getProductVariationsUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductVariationsUseCase.execute(productId)).thenReturn(Collections.emptyList());
        
        productVariationsService.getProductVariations(productId);
        
        verify(getProductVariationsUseCase).execute(productId);
    }

    private ProductVariation createProductVariation() {
        ProductVariation variation = new ProductVariation();
        variation.setAvailableColors(Arrays.asList("Preto", "Azul"));
        variation.setStorageOptions(Arrays.asList("128GB", "256GB"));
        variation.setRamOptions(Arrays.asList("8GB", "12GB"));
        return variation;
    }
}
