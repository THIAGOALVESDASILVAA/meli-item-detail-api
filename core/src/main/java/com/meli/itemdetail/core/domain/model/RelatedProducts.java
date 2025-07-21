package com.meli.itemdetail.core.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class RelatedProducts {
    private List<RelatedProduct> products;

    public RelatedProducts() {}

    public RelatedProducts(List<RelatedProduct> products) {
        this.products = products;
    }

    public List<RelatedProduct> getProducts() { return products; }
    public void setProducts(List<RelatedProduct> products) { this.products = products; }

    public static class RelatedProduct {
        private String id;
        private String name;
        private BigDecimal price;
        private BigDecimal discountPercentage;
        private String installment;
        private String image;

        public RelatedProduct() {}

        public RelatedProduct(String id, String name, BigDecimal price, BigDecimal discountPercentage, String installment, String image) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.discountPercentage = discountPercentage;
            this.installment = installment;
            this.image = image;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }

        public BigDecimal getDiscountPercentage() { return discountPercentage; }
        public void setDiscountPercentage(BigDecimal discountPercentage) { this.discountPercentage = discountPercentage; }

        public String getInstallment() { return installment; }
        public void setInstallment(String installment) { this.installment = installment; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }
}
