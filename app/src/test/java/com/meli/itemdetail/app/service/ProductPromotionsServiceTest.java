package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductPromotions;
import com.meli.itemdetail.core.usecase.GetProductPromotionsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductPromotionsServiceTest {

    @Mock
    private GetProductPromotionsUseCase getProductPromotionsUseCase;

    private ProductPromotionsService productPromotionsService;

    @BeforeEach
    void setUp() {
        productPromotionsService = new ProductPromotionsService(getProductPromotionsUseCase);
    }

    @Test
    void shouldReturnProductPromotionsWhenUseCaseReturnsPromotions() {
        String productId = "MLB123456789";
        ProductPromotions expectedPromotions = createProductPromotions();
        
        when(getProductPromotionsUseCase.execute(productId)).thenReturn(expectedPromotions);
        
        ProductPromotions result = productPromotionsService.getProductPromotions(productId);
        
        assertEquals(expectedPromotions, result);
        assertEquals(2, result.getCoupons().size());
        assertEquals("Flash Sale", result.getCoupons().get(0).getTitle());
        verify(getProductPromotionsUseCase).execute(productId);
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        String productId = "MLB999999999";
        
        when(getProductPromotionsUseCase.execute(productId)).thenReturn(null);
        
        ProductPromotions result = productPromotionsService.getProductPromotions(productId);
        
        assertNull(result);
        verify(getProductPromotionsUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductPromotionsUseCase.execute(productId)).thenReturn(createProductPromotions());
        
        productPromotionsService.getProductPromotions(productId);
        
        verify(getProductPromotionsUseCase).execute(productId);
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
