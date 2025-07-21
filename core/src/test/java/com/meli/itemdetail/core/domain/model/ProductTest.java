package com.meli.itemdetail.core.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProductWithAllFields() {
        LocalDateTime createdDate = LocalDateTime.now();
        Product product = new Product();
        product.setId("MLB123456789");
        product.setTitle("iPhone 13 128GB Azul");
        product.setPrice(new BigDecimal("3299.99"));
        product.setCondition("new");
        product.setFreeShipping(true);
        product.setCreated(createdDate);
        product.setCategory("Celulares");
        product.setShortDescription("iPhone 13 com tela Super Retina XDR");

        assertEquals("MLB123456789", product.getId());
        assertEquals("iPhone 13 128GB Azul", product.getTitle());
        assertEquals(new BigDecimal("3299.99"), product.getPrice());
        assertEquals("new", product.getCondition());
        assertTrue(product.getFreeShipping());
        assertEquals(createdDate, product.getCreated());
        assertEquals("Celulares", product.getCategory());
        assertEquals("iPhone 13 com tela Super Retina XDR", product.getShortDescription());
    }

    @Test
    void shouldCreateEmptyProduct() {
        Product product = new Product();

        assertNull(product.getId());
        assertNull(product.getTitle());
        assertNull(product.getPrice());
        assertNull(product.getCondition());
        assertNull(product.getFreeShipping());
        assertNull(product.getCreated());
        assertNull(product.getCategory());
        assertNull(product.getShortDescription());
    }
}
