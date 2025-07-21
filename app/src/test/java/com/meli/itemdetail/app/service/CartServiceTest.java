package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.usecase.AddToCartUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private AddToCartUseCase addToCartUseCase;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService(addToCartUseCase);
    }

    @Test
    void shouldAddItemToCartWhenValidParameters() {
        String productId = "MLB123456789";
        int quantity = 2;
        
        cartService.addToCart(productId, quantity);
        
        verify(addToCartUseCase).execute(productId, quantity);
    }

    @Test
    void shouldAddSingleItemToCart() {
        String productId = "MLB987654321";
        int quantity = 1;
        
        cartService.addToCart(productId, quantity);
        
        verify(addToCartUseCase).execute(productId, quantity);
    }

    @Test
    void shouldAddMultipleItemsToCart() {
        String productId = "MLB555666777";
        int quantity = 10;
        
        cartService.addToCart(productId, quantity);
        
        verify(addToCartUseCase).execute(productId, quantity);
    }

    @Test
    void shouldDelegateToUseCaseWithCorrectParameters() {
        String productId = "MLB111222333";
        int quantity = 5;
        
        cartService.addToCart(productId, quantity);
        
        verify(addToCartUseCase).execute(productId, quantity);
        verifyNoMoreInteractions(addToCartUseCase);
    }
}
