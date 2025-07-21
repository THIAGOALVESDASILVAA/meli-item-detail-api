package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.usecase.GetBrandProductsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandProductsServiceTest {

    @Mock
    private GetBrandProductsUseCase getBrandProductsUseCase;

    private BrandProductsService brandProductsService;

    @BeforeEach
    void setUp() {
        brandProductsService = new BrandProductsService(getBrandProductsUseCase);
    }

    @Test
    void shouldReturnProductsWhenUseCaseReturnsProducts() {
        String brandName = "Samsung";
        List<Product> expectedProducts = Arrays.asList(
            createProduct("MLB111", "Samsung Galaxy S23"),
            createProduct("MLB222", "Samsung Galaxy A54")
        );
        
        when(getBrandProductsUseCase.execute(brandName)).thenReturn(expectedProducts);
        
        List<Product> result = brandProductsService.getBrandProducts(brandName);
        
        assertEquals(expectedProducts, result);
        assertEquals(2, result.size());
        assertEquals("Samsung Galaxy S23", result.get(0).getTitle());
        verify(getBrandProductsUseCase).execute(brandName);
    }

    @Test
    void shouldReturnEmptyListWhenUseCaseReturnsEmptyList() {
        String brandName = "NonExistentBrand";
        
        when(getBrandProductsUseCase.execute(brandName)).thenReturn(Collections.emptyList());
        
        List<Product> result = brandProductsService.getBrandProducts(brandName);
        
        assertTrue(result.isEmpty());
        verify(getBrandProductsUseCase).execute(brandName);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectBrandName() {
        String brandName = "Apple";
        
        when(getBrandProductsUseCase.execute(brandName)).thenReturn(Collections.emptyList());
        
        brandProductsService.getBrandProducts(brandName);
        
        verify(getBrandProductsUseCase).execute(brandName);
    }

    private Product createProduct(String id, String title) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(new BigDecimal("1299.99"));
        return product;
    }
}
