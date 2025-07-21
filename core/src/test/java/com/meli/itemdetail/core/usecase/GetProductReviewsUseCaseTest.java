package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductReviews;
import com.meli.itemdetail.core.domain.port.ProductReviewsRepositoryPort;
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
class GetProductReviewsUseCaseTest {

    @Mock
    private ProductReviewsRepositoryPort repository;

    private GetProductReviewsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetProductReviewsUseCase(repository);
    }

    @Test
    void shouldReturnProductReviewsFromRepository() {
        String productId = "MLB123456789";
        ProductReviews expectedReviews = createProductReviews();
        
        when(repository.findByProductId(productId)).thenReturn(expectedReviews);
        
        ProductReviews result = useCase.execute(productId);
        
        assertNotNull(result);
        assertEquals(expectedReviews, result);
        assertEquals(new BigDecimal("4.5"), result.getAverageRating());
        assertEquals(100, result.getReviewCount());
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldReturnNullWhenNoReviewsFound() {
        String productId = "MLB999999999";
        
        when(repository.findByProductId(productId)).thenReturn(null);
        
        ProductReviews result = useCase.execute(productId);
        
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

    private ProductReviews createProductReviews() {
        ProductReviews reviews = new ProductReviews();
        reviews.setAverageRating(new BigDecimal("4.5"));
        reviews.setReviewCount(100);
        reviews.setComments(Arrays.asList(
            new ProductReviews.Comment("João", 5, "Excellent product!", "2024-01-15"),
            new ProductReviews.Comment("Maria", 4, "Good quality", "2024-01-10")
        ));
        return reviews;
    }
}
