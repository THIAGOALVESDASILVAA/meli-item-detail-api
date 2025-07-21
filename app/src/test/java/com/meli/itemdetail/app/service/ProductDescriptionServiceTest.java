package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductDescription;
import com.meli.itemdetail.core.usecase.GetProductDescriptionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductDescriptionServiceTest {

    @Mock
    private GetProductDescriptionUseCase getProductDescriptionUseCase;

    private ProductDescriptionService productDescriptionService;

    @BeforeEach
    void setUp() {
        productDescriptionService = new ProductDescriptionService(getProductDescriptionUseCase);
    }

    @Test
    void shouldReturnProductDescriptionWhenUseCaseReturnsDescription() {
        String productId = "MLB123456789";
        ProductDescription expectedDescription = createProductDescription();
        
        when(getProductDescriptionUseCase.execute(productId)).thenReturn(expectedDescription);
        
        ProductDescription result = productDescriptionService.getProductDescription(productId);
        
        assertEquals(expectedDescription, result);
        assertEquals(2, result.getBlocks().size());
        assertEquals("Características Principais", result.getBlocks().get(0).getTitle());
        verify(getProductDescriptionUseCase).execute(productId);
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        String productId = "MLB999999999";
        
        when(getProductDescriptionUseCase.execute(productId)).thenReturn(null);
        
        ProductDescription result = productDescriptionService.getProductDescription(productId);
        
        assertNull(result);
        verify(getProductDescriptionUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductDescriptionUseCase.execute(productId)).thenReturn(createProductDescription());
        
        productDescriptionService.getProductDescription(productId);
        
        verify(getProductDescriptionUseCase).execute(productId);
    }

    private ProductDescription createProductDescription() {
        ProductDescription description = new ProductDescription();
        description.setBlocks(Arrays.asList(
            new ProductDescription.DescriptionBlock("Características Principais", "Tela Super Retina XDR\nChip A15 Bionic", "features"),
            new ProductDescription.DescriptionBlock("Garantia", "1 ano de garantia", "warranty")
        ));
        return description;
    }
}
