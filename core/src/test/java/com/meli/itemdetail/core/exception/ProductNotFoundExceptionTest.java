package com.meli.itemdetail.core.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithProductId() {
        String productId = "MLB123456789";
        
        ProductNotFoundException exception = new ProductNotFoundException(productId);
        
        assertEquals("Product not found with id: " + productId, exception.getMessage());
        assertEquals(productId, exception.getProductId());
    }

    @Test
    void shouldInheritFromRuntimeException() {
        ProductNotFoundException exception = new ProductNotFoundException("MLB123456789");
        
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    void shouldHandleNullProductId() {
        ProductNotFoundException exception = new ProductNotFoundException(null);
        
        assertEquals("Product not found with id: null", exception.getMessage());
        assertNull(exception.getProductId());
    }

    @Test
    void shouldHandleEmptyProductId() {
        String productId = "";
        
        ProductNotFoundException exception = new ProductNotFoundException(productId);
        
        assertEquals("Product not found with id: ", exception.getMessage());
        assertEquals(productId, exception.getProductId());
    }

    @Test
    void shouldPreserveOriginalProductIdValue() {
        String productId = "MLB987654321";
        
        ProductNotFoundException exception = new ProductNotFoundException(productId);
        
        assertEquals(productId, exception.getProductId());
        assertTrue(exception.getMessage().contains(productId));
    }

    @Test
    void shouldCreateConsistentMessage() {
        String productId = "MLB555555555";
        
        ProductNotFoundException exception = new ProductNotFoundException(productId);
        
        String expectedMessage = "Product not found with id: " + productId;
        assertEquals(expectedMessage, exception.getMessage());
    }
}
