package com.meli.itemdetail.restadapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.app.service.CartService;
import com.meli.itemdetail.restadapter.RestAdapterApplication;
import com.meli.itemdetail.restadapter.dto.AddToCartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
@ContextConfiguration(classes = RestAdapterApplication.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartService cartService;

    @Test
    void shouldAddProductToCartSuccessfully() throws Exception {
        AddToCartRequest request = new AddToCartRequest("MLB123456789", 2);
        
        mockMvc.perform(post("/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
        
        verify(cartService).addToCart("MLB123456789", 2);
    }

    @Test
    void shouldAddSingleProductToCart() throws Exception {
        AddToCartRequest request = new AddToCartRequest("MLB987654321", 1);
        
        mockMvc.perform(post("/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
        
        verify(cartService).addToCart("MLB987654321", 1);
    }

    @Test
    void shouldAddMultipleQuantityToCart() throws Exception {
        AddToCartRequest request = new AddToCartRequest("MLB555666777", 5);
        
        mockMvc.perform(post("/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
        
        verify(cartService).addToCart("MLB555666777", 5);
    }
}
