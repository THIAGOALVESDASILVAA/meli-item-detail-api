package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductVariation;
import com.meli.itemdetail.core.domain.port.ProductVariationsRepositoryPort;
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
class GetProductVariationsUseCaseTest {

    @Mock
    private ProductVariationsRepositoryPort repository;

    private GetProductVariationsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetProductVariationsUseCase(repository);
    }

    @Test
    void shouldReturnProductVariationsFromRepository() {
        String productId = "MLB123456789";
        List<ProductVariation> expectedVariations = Arrays.asList(
            createProductVariation(Arrays.asList("Preto", "Azul"), Arrays.asList("128GB", "256GB"), Arrays.asList())
        );
        
        when(repository.findByProductId(productId)).thenReturn(expectedVariations);
        
        List<ProductVariation> result = useCase.execute(productId);
        
        assertNotNull(result);
        assertEquals(expectedVariations, result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getAvailableColors().size());
        assertEquals(2, result.get(0).getStorageOptions().size());
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldReturnEmptyListWhenNoVariationsFound() {
        String productId = "MLB999999999";
        
        when(repository.findByProductId(productId)).thenReturn(Collections.emptyList());
        
        List<ProductVariation> result = useCase.execute(productId);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldCallRepositoryWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(repository.findByProductId(productId)).thenReturn(Collections.emptyList());
        
        useCase.execute(productId);
        
        verify(repository).findByProductId(productId);
    }

    private ProductVariation createProductVariation(List<String> colors, List<String> storage, List<String> ram) {
        ProductVariation variation = new ProductVariation();
        variation.setAvailableColors(colors);
        variation.setStorageOptions(storage);
        variation.setRamOptions(ram);
        return variation;
    }
}
