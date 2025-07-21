package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductShipping;
import com.meli.itemdetail.core.domain.port.ProductShippingRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductShippingUseCaseTest {

    @Mock
    private ProductShippingRepositoryPort repository;

    private GetProductShippingUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetProductShippingUseCase(repository);
    }

    @Test
    void shouldReturnProductShippingFromRepository() {
        String productId = "MLB123456789";
        ProductShipping expectedShipping = createProductShipping();
        
        when(repository.findByProductId(productId)).thenReturn(expectedShipping);
        
        ProductShipping result = useCase.execute(productId);
        
        assertNotNull(result);
        assertEquals(expectedShipping, result);
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldReturnNullWhenNoShippingFound() {
        String productId = "MLB999999999";
        
        when(repository.findByProductId(productId)).thenReturn(null);
        
        ProductShipping result = useCase.execute(productId);
        
        assertNull(result);
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldCallRepositoryWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(repository.findByProductId(productId)).thenReturn(null);
        
        useCase.execute(productId);
        
        verify(repository).findByProductId(productId);
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
