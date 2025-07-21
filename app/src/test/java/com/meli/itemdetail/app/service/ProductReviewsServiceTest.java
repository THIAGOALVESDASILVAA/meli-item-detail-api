package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductReviews;
import com.meli.itemdetail.core.usecase.GetProductReviewsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductReviewsServiceTest {

    @Mock
    private GetProductReviewsUseCase getProductReviewsUseCase;

    private ProductReviewsService productReviewsService;

    @BeforeEach
    void setUp() {
        productReviewsService = new ProductReviewsService(getProductReviewsUseCase);
    }

    @Test
    void shouldReturnProductReviewsWhenUseCaseReturnsReviews() {
        String productId = "MLB123456789";
        ProductReviews expectedReviews = createProductReviews();
        
        when(getProductReviewsUseCase.execute(productId)).thenReturn(expectedReviews);
        
        ProductReviews result = productReviewsService.getProductReviews(productId);
        
        assertEquals(expectedReviews, result);
        assertEquals(new BigDecimal("4.5"), result.getAverageRating());
        assertEquals(50, result.getReviewCount());
        verify(getProductReviewsUseCase).execute(productId);
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        String productId = "MLB999999999";
        
        when(getProductReviewsUseCase.execute(productId)).thenReturn(null);
        
        ProductReviews result = productReviewsService.getProductReviews(productId);
        
        assertNull(result);
        verify(getProductReviewsUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductReviewsUseCase.execute(productId)).thenReturn(createProductReviews());
        
        productReviewsService.getProductReviews(productId);
        
        verify(getProductReviewsUseCase).execute(productId);
    }

    private ProductReviews createProductReviews() {
        ProductReviews reviews = new ProductReviews();
        reviews.setAverageRating(new BigDecimal("4.5"));
        reviews.setReviewCount(50);
        reviews.setComments(Arrays.asList(
            new ProductReviews.Comment("João", 5, "Excellent product!", "2024-01-15"),
            new ProductReviews.Comment("Maria", 4, "Good quality", "2024-01-10")
        ));
        return reviews;
    }
}
