package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.usecase.GetProductByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private GetProductByIdUseCase getProductByIdUseCase;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(getProductByIdUseCase);
    }

    @Test
    void shouldReturnProductWhenUseCaseReturnsProduct() {
        String productId = "MLB123456789";
        Product expectedProduct = createProduct(productId);
        
        when(getProductByIdUseCase.execute(productId)).thenReturn(Optional.of(expectedProduct));
        
        Optional<Product> result = productService.getProductById(productId);
        
        assertTrue(result.isPresent());
        assertEquals(expectedProduct, result.get());
        assertEquals(productId, result.get().getId());
        verify(getProductByIdUseCase).execute(productId);
    }

    @Test
    void shouldReturnEmptyWhenUseCaseReturnsEmpty() {
        String productId = "MLB999999999";
        
        when(getProductByIdUseCase.execute(productId)).thenReturn(Optional.empty());
        
        Optional<Product> result = productService.getProductById(productId);
        
        assertTrue(result.isEmpty());
        verify(getProductByIdUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductByIdUseCase.execute(productId)).thenReturn(Optional.empty());
        
        productService.getProductById(productId);
        
        verify(getProductByIdUseCase).execute(productId);
    }

    private Product createProduct(String productId) {
        Product product = new Product();
        product.setId(productId);
        product.setTitle("Test Product");
        product.setPrice(new BigDecimal("999.99"));
        return product;
    }
}
