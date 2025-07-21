package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductImages;
import com.meli.itemdetail.core.usecase.GetProductImagesUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductImagesServiceTest {

    @Mock
    private GetProductImagesUseCase getProductImagesUseCase;

    private ProductImagesService productImagesService;

    @BeforeEach
    void setUp() {
        productImagesService = new ProductImagesService(getProductImagesUseCase);
    }

    @Test
    void shouldReturnProductImagesWhenUseCaseReturnsImages() {
        String productId = "MLB123456789";
        ProductImages expectedImages = createProductImages();
        
        when(getProductImagesUseCase.execute(productId)).thenReturn(expectedImages);
        
        ProductImages result = productImagesService.getProductImages(productId);
        
        assertEquals(expectedImages, result);
        assertEquals(3, result.getImages().size());
        verify(getProductImagesUseCase).execute(productId);
    }

    @Test
    void shouldReturnEmptyImagesWhenUseCaseReturnsEmpty() {
        String productId = "MLB999999999";
        ProductImages emptyImages = new ProductImages();
        
        when(getProductImagesUseCase.execute(productId)).thenReturn(emptyImages);
        
        ProductImages result = productImagesService.getProductImages(productId);
        
        assertEquals(emptyImages, result);
        assertNull(result.getImages());
        verify(getProductImagesUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductImagesUseCase.execute(productId)).thenReturn(new ProductImages());
        
        productImagesService.getProductImages(productId);
        
        verify(getProductImagesUseCase).execute(productId);
    }

    private ProductImages createProductImages() {
        return new ProductImages(Arrays.asList(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg", 
            "https://example.com/image3.jpg"
        ));
    }
}
