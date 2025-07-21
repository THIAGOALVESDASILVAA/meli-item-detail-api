package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductPromotions;
import com.meli.itemdetail.core.domain.port.ProductPromotionsRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductPromotionsUseCaseTest {

    @Mock
    private ProductPromotionsRepositoryPort repository;

    private GetProductPromotionsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetProductPromotionsUseCase(repository);
    }

    @Test
    void shouldReturnProductPromotionsFromRepository() {
        String productId = "MLB123456789";
        ProductPromotions expectedPromotions = createProductPromotions();
        
        when(repository.findByProductId(productId)).thenReturn(expectedPromotions);
        
        ProductPromotions result = useCase.execute(productId);
        
        assertNotNull(result);
        assertEquals(expectedPromotions, result);
        assertEquals(2, result.getCoupons().size());
        assertEquals("Flash Sale", result.getCoupons().get(0).getTitle());
        assertEquals("discount", result.getCoupons().get(0).getType());
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldReturnNullWhenNoPromotionsFound() {
        String productId = "MLB999999999";
        
        when(repository.findByProductId(productId)).thenReturn(null);
        
        ProductPromotions result = useCase.execute(productId);
        
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

    private ProductPromotions createProductPromotions() {
        ProductPromotions promotions = new ProductPromotions();
        promotions.setCoupons(Arrays.asList(
            new ProductPromotions.Coupon("Flash Sale", "15% discount for PIX payment", "discount", ""),
            new ProductPromotions.Coupon("Installment", "Up to 6x interest-free", "installment", "")
        ));
        return promotions;
    }
}
