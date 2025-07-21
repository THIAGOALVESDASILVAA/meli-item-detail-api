package com.meli.itemdetail.restadapter.controller;

import com.meli.itemdetail.app.service.*;
import com.meli.itemdetail.core.domain.model.*;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductImagesService productImagesService;
    private final ProductReviewsService productReviewsService;
    private final ProductVariationsService productVariationsService;
    private final ProductPromotionsService productPromotionsService;
    private final ProductShippingService productShippingService;
    private final ProductSellerService productSellerService;
    private final RelatedProductsService relatedProductsService;
    private final TechnicalSpecificationsService technicalSpecificationsService;
    private final ProductDescriptionService productDescriptionService;

    public ProductController(
            ProductService productService,
            ProductImagesService productImagesService,
            ProductReviewsService productReviewsService,
            ProductVariationsService productVariationsService,
            ProductPromotionsService productPromotionsService,
            ProductShippingService productShippingService,
            ProductSellerService productSellerService,
            RelatedProductsService relatedProductsService,
            TechnicalSpecificationsService technicalSpecificationsService,
            ProductDescriptionService productDescriptionService) {
        this.productService = productService;
        this.productImagesService = productImagesService;
        this.productReviewsService = productReviewsService;
        this.productVariationsService = productVariationsService;
        this.productPromotionsService = productPromotionsService;
        this.productShippingService = productShippingService;
        this.productSellerService = productSellerService;
        this.relatedProductsService = relatedProductsService;
        this.technicalSpecificationsService = technicalSpecificationsService;
        this.productDescriptionService = productDescriptionService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        setMDCContext(productId);
        return productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{productId}/images")
    public ResponseEntity<ProductImages> getProductImages(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(productImagesService.getProductImages(productId));
    }

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<ProductReviews> getProductReviews(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(productReviewsService.getProductReviews(productId));
    }

    @GetMapping("/{productId}/variations")
    public ResponseEntity<List<ProductVariation>> getProductVariations(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(productVariationsService.getProductVariations(productId));
    }

    @GetMapping("/{productId}/promotions")
    public ResponseEntity<ProductPromotions> getProductPromotions(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(productPromotionsService.getProductPromotions(productId));
    }

    @GetMapping("/{productId}/shipping")
    public ResponseEntity<ProductShipping> getProductShipping(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(productShippingService.getProductShipping(productId));
    }

    @GetMapping("/{productId}/seller")
    public ResponseEntity<ProductSeller> getProductSeller(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(productSellerService.getProductSeller(productId));
    }

    @GetMapping("/{productId}/related")
    public ResponseEntity<RelatedProducts> getRelatedProducts(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(relatedProductsService.getRelatedProducts(productId));
    }

    @GetMapping("/{productId}/technical-specifications")
    public ResponseEntity<TechnicalSpecifications> getTechnicalSpecifications(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(technicalSpecificationsService.getTechnicalSpecifications(productId));
    }

    @GetMapping("/{productId}/description")
    public ResponseEntity<ProductDescription> getProductDescription(@PathVariable String productId) {
        setMDCContext(productId);
        return ResponseEntity.ok(productDescriptionService.getProductDescription(productId));
    }

    private void setMDCContext(String productId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("productId", productId);
    }
}
