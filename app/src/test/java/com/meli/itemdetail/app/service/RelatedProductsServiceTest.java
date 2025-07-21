package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.RelatedProducts;
import com.meli.itemdetail.core.usecase.GetRelatedProductsUseCase;
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
class RelatedProductsServiceTest {

    @Mock
    private GetRelatedProductsUseCase getRelatedProductsUseCase;

    private RelatedProductsService relatedProductsService;

    @BeforeEach
    void setUp() {
        relatedProductsService = new RelatedProductsService(getRelatedProductsUseCase);
    }

    @Test
    void shouldReturnRelatedProductsWhenUseCaseReturnsProducts() {
        String productId = "MLB123456789";
        RelatedProducts expectedRelatedProducts = createRelatedProducts();
        
        when(getRelatedProductsUseCase.execute(productId)).thenReturn(expectedRelatedProducts);
        
        RelatedProducts result = relatedProductsService.getRelatedProducts(productId);
        
        assertEquals(expectedRelatedProducts, result);
        assertEquals(2, result.getProducts().size());
        assertEquals("iPhone 14", result.getProducts().get(0).getName());
        verify(getRelatedProductsUseCase).execute(productId);
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        String productId = "MLB999999999";
        
        when(getRelatedProductsUseCase.execute(productId)).thenReturn(null);
        
        RelatedProducts result = relatedProductsService.getRelatedProducts(productId);
        
        assertNull(result);
        verify(getRelatedProductsUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getRelatedProductsUseCase.execute(productId)).thenReturn(createRelatedProducts());
        
        relatedProductsService.getRelatedProducts(productId);
        
        verify(getRelatedProductsUseCase).execute(productId);
    }

    private RelatedProducts createRelatedProducts() {
        RelatedProducts relatedProducts = new RelatedProducts();
        
        RelatedProducts.RelatedProduct product1 = new RelatedProducts.RelatedProduct();
        product1.setId("MLB123456788");
        product1.setName("iPhone 14");
        product1.setPrice(new BigDecimal("3999.99"));
        product1.setImage("https://http2.mlstatic.com/D_123456-MLA1234567890_012024-O.jpg");
        
        RelatedProducts.RelatedProduct product2 = new RelatedProducts.RelatedProduct();
        product2.setId("MLB123456790");
        product2.setName("iPhone 15 Plus");
        product2.setPrice(new BigDecimal("5499.99"));
        product2.setImage("https://http2.mlstatic.com/D_123457-MLA1234567891_012024-O.jpg");
        
        relatedProducts.setProducts(Arrays.asList(product1, product2));
        
        return relatedProducts;
    }
}
