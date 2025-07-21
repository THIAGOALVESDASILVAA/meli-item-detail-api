package com.meli.itemdetail.infrastructure.config;

import com.meli.itemdetail.core.domain.port.*;
import com.meli.itemdetail.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetProductByIdUseCase getProductByIdUseCase(ProductRepositoryPort productRepositoryPort,
            ProductCachePort productCachePort) {
        return new GetProductByIdUseCase(productRepositoryPort, productCachePort);
    }

    @Bean
    public GetBrandProductsUseCase getBrandProductsUseCase(BrandProductsRepositoryPort brandProductsRepositoryPort) {
        return new GetBrandProductsUseCase(brandProductsRepositoryPort);
    }

    @Bean
    public GetProductImagesUseCase getProductImagesUseCase(ProductImagesRepositoryPort productImagesRepositoryPort) {
        return new GetProductImagesUseCase(productImagesRepositoryPort);
    }

    @Bean
    public GetProductReviewsUseCase getProductReviewsUseCase(
            ProductReviewsRepositoryPort productReviewsRepositoryPort) {
        return new GetProductReviewsUseCase(productReviewsRepositoryPort);
    }

    @Bean
    public GetProductVariationsUseCase getProductVariationsUseCase(
            ProductVariationsRepositoryPort productVariationsRepositoryPort) {
        return new GetProductVariationsUseCase(productVariationsRepositoryPort);
    }

    @Bean
    public GetProductPromotionsUseCase getProductPromotionsUseCase(
            ProductPromotionsRepositoryPort productPromotionsRepositoryPort) {
        return new GetProductPromotionsUseCase(productPromotionsRepositoryPort);
    }

    @Bean
    public GetProductShippingUseCase getProductShippingUseCase(
            ProductShippingRepositoryPort productShippingRepositoryPort) {
        return new GetProductShippingUseCase(productShippingRepositoryPort);
    }

    @Bean
    public GetProductSellerUseCase getProductSellerUseCase(ProductSellerRepositoryPort productSellerRepositoryPort) {
        return new GetProductSellerUseCase(productSellerRepositoryPort);
    }

    @Bean
    public GetRelatedProductsUseCase getRelatedProductsUseCase(
            RelatedProductsRepositoryPort relatedProductsRepositoryPort) {
        return new GetRelatedProductsUseCase(relatedProductsRepositoryPort);
    }

    @Bean
    public GetTechnicalSpecificationsUseCase getTechnicalSpecificationsUseCase(
            TechnicalSpecificationsRepositoryPort technicalSpecificationsRepositoryPort) {
        return new GetTechnicalSpecificationsUseCase(technicalSpecificationsRepositoryPort);
    }

    @Bean
    public GetProductDescriptionUseCase getProductDescriptionUseCase(
            ProductDescriptionRepositoryPort productDescriptionRepositoryPort) {
        return new GetProductDescriptionUseCase(productDescriptionRepositoryPort);
    }

    @Bean
    public GetPaymentOptionsUseCase getPaymentOptionsUseCase(
            PaymentOptionsRepositoryPort paymentOptionsRepositoryPort) {
        return new GetPaymentOptionsUseCase(paymentOptionsRepositoryPort);
    }

    @Bean
    public AddToCartUseCase addToCartUseCase(CartRepositoryPort cartRepositoryPort) {
        return new AddToCartUseCase(cartRepositoryPort);
    }
}
