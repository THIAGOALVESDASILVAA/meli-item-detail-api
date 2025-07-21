package com.meli.itemdetail.restadapter.controller;

import com.meli.itemdetail.app.service.*;
import com.meli.itemdetail.core.domain.model.*;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = RestAdapterApplication.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    
    @MockBean
    private ProductImagesService productImagesService;
    
    @MockBean
    private ProductReviewsService productReviewsService;
    
    @MockBean
    private ProductVariationsService productVariationsService;
    
    @MockBean
    private ProductPromotionsService productPromotionsService;
    
    @MockBean
    private ProductShippingService productShippingService;
    
    @MockBean
    private ProductSellerService productSellerService;
    
    @MockBean
    private RelatedProductsService relatedProductsService;
    
    @MockBean
    private TechnicalSpecificationsService technicalSpecificationsService;
    
    @MockBean
    private ProductDescriptionService productDescriptionService;

    @Test
    void shouldReturnProductWhenProductExists() throws Exception {
        String productId = "MLB123456789";
        Product product = createProduct(productId, "iPhone 15 Pro Max");
        
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));
        
        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.title").value("iPhone 15 Pro Max"))
                .andExpect(jsonPath("$.price").value(5999.99));
    }

    @Test
    void shouldReturnProductImagesWhenProductExists() throws Exception {
        String productId = "MLB123456789";
        ProductImages images = createProductImages();
        
        when(productImagesService.getProductImages(productId)).thenReturn(images);
        
        mockMvc.perform(get("/products/{id}/images", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.images[0]").value("https://example.com/image1.jpg"));
    }

    @Test
    void shouldReturnProductReviewsWhenProductExists() throws Exception {
        String productId = "MLB123456789";
        ProductReviews reviews = createProductReviews();
        
        when(productReviewsService.getProductReviews(productId)).thenReturn(reviews);
        
        mockMvc.perform(get("/products/{id}/reviews", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.averageRating").value(4.5));
    }

    @Test
    void shouldReturnProductVariationsWhenProductExists() throws Exception {
        String productId = "MLB123456789";
        List<ProductVariation> variations = createProductVariations();
        
        when(productVariationsService.getProductVariations(productId)).thenReturn(variations);
        
        mockMvc.perform(get("/products/{id}/variations", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].availableColors[0]").value("Preto"));
    }

    @Test
    void shouldReturnTechnicalSpecificationsWhenProductExists() throws Exception {
        String productId = "MLB123456789";
        TechnicalSpecifications specs = createTechnicalSpecifications();
        
        when(technicalSpecificationsService.getTechnicalSpecifications(productId)).thenReturn(specs);
        
        mockMvc.perform(get("/products/{id}/technical-specifications", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.screen").value("6.7 polegadas"))
                .andExpect(jsonPath("$.internalMemory").value("256GB"));
    }

    @Test
    void shouldReturnRelatedProductsWhenProductExists() throws Exception {
        String productId = "MLB123456789";
        RelatedProducts relatedProducts = createRelatedProducts();
        
        when(relatedProductsService.getRelatedProducts(productId)).thenReturn(relatedProducts);
        
        mockMvc.perform(get("/products/{id}/related", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.products[0].name").value("iPhone 14"));
    }

    private Product createProduct(String id, String title) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(new BigDecimal("5999.99"));
        product.setCondition("new");
        product.setFreeShipping(true);
        product.setCreated(LocalDateTime.now());
        product.setCategory("Smartphones");
        return product;
    }

    private ProductImages createProductImages() {
        ProductImages images = new ProductImages();
        images.setImages(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"));
        return images;
    }

    private ProductReviews createProductReviews() {
        ProductReviews reviews = new ProductReviews();
        reviews.setAverageRating(new BigDecimal("4.5"));
        reviews.setReviewCount(150);
        reviews.setComments(Arrays.asList());
        return reviews;
    }

    private List<ProductVariation> createProductVariations() {
        ProductVariation variation = new ProductVariation();
        variation.setAvailableColors(Arrays.asList("Preto", "Azul"));
        variation.setStorageOptions(Arrays.asList("128GB", "256GB"));
        variation.setRamOptions(Arrays.asList("8GB", "12GB"));
        return Arrays.asList(variation);
    }

    private TechnicalSpecifications createTechnicalSpecifications() {
        TechnicalSpecifications specs = new TechnicalSpecifications();
        specs.setScreen("6.7 polegadas");
        specs.setInternalMemory("256GB");
        specs.setRamMemory("8GB");
        specs.setRearCamera("48MP + 12MP + 12MP");
        specs.setFrontCamera("12MP");
        specs.setUnlockMethod("Face ID");
        specs.setNfc(true);
        return specs;
    }

    private RelatedProducts createRelatedProducts() {
        RelatedProducts relatedProducts = new RelatedProducts();
        
        RelatedProducts.RelatedProduct product = new RelatedProducts.RelatedProduct();
        product.setId("MLB123456788");
        product.setName("iPhone 14");
        product.setPrice(new BigDecimal("3999.99"));
        product.setImage("https://example.com/iphone14.jpg");
        
        relatedProducts.setProducts(Arrays.asList(product));
        return relatedProducts;
    }
}
