package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductSeller;
import com.meli.itemdetail.core.usecase.GetProductSellerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductSellerServiceTest {

    @Mock
    private GetProductSellerUseCase getProductSellerUseCase;

    private ProductSellerService productSellerService;

    @BeforeEach
    void setUp() {
        productSellerService = new ProductSellerService(getProductSellerUseCase);
    }

    @Test
    void shouldReturnProductSellerWhenUseCaseReturnsSeller() {
        String productId = "MLB123456789";
        ProductSeller expectedSeller = createProductSeller();
        
        when(getProductSellerUseCase.execute(productId)).thenReturn(expectedSeller);
        
        ProductSeller result = productSellerService.getProductSeller(productId);
        
        assertEquals(expectedSeller, result);
        assertEquals("Loja Oficial Samsung", result.getStoreName());
        assertEquals(5000, result.getTotalSales());
        verify(getProductSellerUseCase).execute(productId);
    }

    @Test
    void shouldReturnNullWhenUseCaseReturnsNull() {
        String productId = "MLB999999999";
        
        when(getProductSellerUseCase.execute(productId)).thenReturn(null);
        
        ProductSeller result = productSellerService.getProductSeller(productId);
        
        assertNull(result);
        verify(getProductSellerUseCase).execute(productId);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectProductId() {
        String productId = "MLB987654321";
        
        when(getProductSellerUseCase.execute(productId)).thenReturn(createProductSeller());
        
        productSellerService.getProductSeller(productId);
        
        verify(getProductSellerUseCase).execute(productId);
    }

    private ProductSeller createProductSeller() {
        ProductSeller seller = new ProductSeller();
        seller.setStoreName("Loja Oficial Samsung");
        seller.setTotalSales(5000);
        seller.setStoreType("official");
        seller.setPublishedProducts(150);
        return seller;
    }
}
