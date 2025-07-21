package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.domain.port.ProductCachePort;
import com.meli.itemdetail.core.domain.port.ProductRepositoryPort;
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
class GetProductByIdUseCaseTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private ProductCachePort productCachePort;

    private GetProductByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetProductByIdUseCase(productRepositoryPort, productCachePort);
    }

    @Test
    void shouldReturnProductFromCacheWhenExists() {
        String productId = "MLB123456789";
        Product cachedProduct = createProduct(productId, "Cached Product");
        
        when(productCachePort.get(productId)).thenReturn(Optional.of(cachedProduct));
        
        Optional<Product> result = useCase.execute(productId);
        
        assertTrue(result.isPresent());
        assertEquals(cachedProduct, result.get());
        verify(productCachePort).get(productId);
        verifyNoInteractions(productRepositoryPort);
    }

    @Test
    void shouldReturnProductFromRepositoryWhenNotInCache() {
        String productId = "MLB123456789";
        Product repositoryProduct = createProduct(productId, "Repository Product");
        
        when(productCachePort.get(productId)).thenReturn(Optional.empty());
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.of(repositoryProduct));
        
        Optional<Product> result = useCase.execute(productId);
        
        assertTrue(result.isPresent());
        assertEquals(repositoryProduct, result.get());
        verify(productCachePort).get(productId);
        verify(productRepositoryPort).findById(productId);
        verify(productCachePort).put(productId, repositoryProduct);
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {
        String productId = "INVALID_ID";
        
        when(productCachePort.get(productId)).thenReturn(Optional.empty());
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.empty());
        
        Optional<Product> result = useCase.execute(productId);
        
        assertTrue(result.isEmpty());
        verify(productCachePort).get(productId);
        verify(productRepositoryPort).findById(productId);
        verify(productCachePort, never()).put(any(), any());
    }

    private Product createProduct(String id, String title) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(new BigDecimal("999.99"));
        return product;
    }
}
