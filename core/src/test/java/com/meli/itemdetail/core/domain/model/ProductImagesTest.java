package com.meli.itemdetail.core.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductImagesTest {

    @Test
    void shouldCreateProductImagesWithDefaultConstructor() {
        ProductImages productImages = new ProductImages();
        
        assertNull(productImages.getImages());
    }

    @Test
    void shouldCreateProductImagesWithParameterizedConstructor() {
        List<String> imageUrls = Arrays.asList(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg",
            "https://example.com/image3.jpg"
        );
        
        ProductImages productImages = new ProductImages(imageUrls);
        
        assertEquals(imageUrls, productImages.getImages());
        assertEquals(3, productImages.getImages().size());
        assertTrue(productImages.getImages().contains("https://example.com/image1.jpg"));
    }

    @Test
    void shouldSetAndGetImages() {
        ProductImages productImages = new ProductImages();
        List<String> imageUrls = Arrays.asList(
            "https://example.com/product.jpg",
            "https://example.com/product-detail.jpg"
        );
        
        productImages.setImages(imageUrls);
        
        assertEquals(imageUrls, productImages.getImages());
        assertEquals(2, productImages.getImages().size());
    }

    @Test
    void shouldHandleEmptyImagesList() {
        List<String> emptyList = Collections.emptyList();
        ProductImages productImages = new ProductImages(emptyList);
        
        assertEquals(emptyList, productImages.getImages());
        assertTrue(productImages.getImages().isEmpty());
    }

    @Test
    void shouldHandleNullImages() {
        ProductImages productImages = new ProductImages(null);
        
        assertNull(productImages.getImages());
    }

    @Test
    void shouldAllowChangingImages() {
        List<String> initialImages = Arrays.asList("https://example.com/old.jpg");
        List<String> newImages = Arrays.asList("https://example.com/new1.jpg", "https://example.com/new2.jpg");
        
        ProductImages productImages = new ProductImages(initialImages);
        productImages.setImages(newImages);
        
        assertEquals(newImages, productImages.getImages());
        assertEquals(2, productImages.getImages().size());
    }
}
