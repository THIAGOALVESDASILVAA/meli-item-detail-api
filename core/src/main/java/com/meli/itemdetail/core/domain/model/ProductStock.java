package com.meli.itemdetail.core.domain.model;

public class ProductStock {
    private String productId;
    private Integer availableQuantity;
    private String status;
    
    public ProductStock() {}
    
    public ProductStock(String productId, Integer availableQuantity, String status) {
        this.productId = productId;
        this.availableQuantity = availableQuantity;
        this.status = status;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Integer getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(Integer availableQuantity) { this.availableQuantity = availableQuantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
