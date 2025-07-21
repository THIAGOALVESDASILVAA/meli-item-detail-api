package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductShipping;
import com.meli.itemdetail.core.usecase.GetProductShippingUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductShippingServiceTest {

    @Mock
    private GetProductShippingUseCase getProductShippingUseCase;

    private ProductShippingService productShippingService;

    @BeforeEach
    void setUp() {
        productShippingService = new ProductShippingService(getProductShippingUseCase);
    }

    @Test
    void shouldReturnProductShippingWhenUseCaseReturnsShipping() {
        String productId = "MLB123456789";
        ProductShipping expectedShipping = createProductShipping();
        
        when(getProductShippingUseCase.execute(productId)).thenReturn(expectedShipping);
        
        ProductShipping result = productShippingService.getProductShipping(productId);
        
        assertEquals(expectedShipping, result);
        assertTrue(result.getFreeShipping());
        assertEquals(3, result.getEstimatedDeliveryDays());
        verify(getProductShippingUseCase).execute(productId);
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        String productId = "MLB999999999";
        
        when(getProductShippingUseCase.execute(productId)).thenReturn(null);
        
        ProductShipping result = productShippingService.getProductShipping(productId);
        
        assertNull(result);
        verify(getProductShippingUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductShippingUseCase.execute(productId)).thenReturn(createProductShipping());
        
        productShippingService.getProductShipping(productId);
        
        verify(getProductShippingUseCase).execute(productId);
    }

    private ProductShipping createProductShipping() {
        ProductShipping shipping = new ProductShipping();
        shipping.setFreeShipping(true);
        shipping.setEstimatedDeliveryDays(3);
        shipping.setOrigin("São Paulo - SP");
        shipping.setCarrier("Mercado Envios");
        return shipping;
    }
}
