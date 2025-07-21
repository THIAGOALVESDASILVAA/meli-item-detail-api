package com.meli.itemdetail.restadapter.controller;

import com.meli.itemdetail.app.service.BrandProductsService;
import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.restadapter.RestAdapterApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
@ContextConfiguration(classes = RestAdapterApplication.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandProductsService brandProductsService;

    @Test
    void shouldReturnBrandProductsWhenBrandExists() throws Exception {
        String brand = "Apple";
        Product product1 = createProduct("MLB111", "iPhone 15");
        Product product2 = createProduct("MLB222", "iPhone 14");
        
        when(brandProductsService.getBrandProducts(brand)).thenReturn(Arrays.asList(product1, product2));
        
        mockMvc.perform(get("/brands/{brand}/products", brand))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value("MLB111"))
                .andExpect(jsonPath("$[0].title").value("iPhone 15"))
                .andExpect(jsonPath("$[1].id").value("MLB222"))
                .andExpect(jsonPath("$[1].title").value("iPhone 14"));
    }

    @Test
    void shouldReturnEmptyListWhenBrandHasNoProducts() throws Exception {
        String brand = "NonExistentBrand";
        
        when(brandProductsService.getBrandProducts(brand)).thenReturn(Collections.emptyList());
        
        mockMvc.perform(get("/brands/{brand}/products", brand))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnSamsungProductsWhenBrandIsSamsung() throws Exception {
        String brand = "Samsung";
        Product product = createProduct("MLB333", "Galaxy S24");
        
        when(brandProductsService.getBrandProducts(brand)).thenReturn(Arrays.asList(product));
        
        mockMvc.perform(get("/brands/{brand}/products", brand))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Galaxy S24"));
    }

    private Product createProduct(String id, String title) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(new BigDecimal("2999.99"));
        product.setCondition("new");
        product.setFreeShipping(true);
        product.setCreated(LocalDateTime.now());
        product.setCategory("Smartphones");
        return product;
    }
}
