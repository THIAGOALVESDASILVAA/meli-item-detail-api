package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductImages;
import com.meli.itemdetail.core.domain.port.ProductImagesRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductImagesUseCaseTest {

    @Mock
    private ProductImagesRepositoryPort repository;

    private GetProductImagesUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetProductImagesUseCase(repository);
    }

    @Test
    void shouldReturnProductImagesWhenFound() {
        String productId = "MLB123456789";
        ProductImages expectedImages = new ProductImages(Arrays.asList(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg"
        ));
        
        when(repository.findByProductId(productId)).thenReturn(Optional.of(expectedImages));
        
        ProductImages result = useCase.execute(productId);
        
        assertNotNull(result);
        assertEquals(expectedImages, result);
        assertEquals(2, result.getImages().size());
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldReturnEmptyProductImagesWhenNotFound() {
        String productId = "MLB999999999";
        
        when(repository.findByProductId(productId)).thenReturn(Optional.empty());
        
        ProductImages result = useCase.execute(productId);
        
        assertNotNull(result);
        assertNull(result.getImages());
        verify(repository).findByProductId(productId);
    }

    @Test
    void shouldCallRepositoryWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(repository.findByProductId(productId)).thenReturn(Optional.empty());
        
        useCase.execute(productId);
        
        verify(repository).findByProductId(productId);
    }
}
