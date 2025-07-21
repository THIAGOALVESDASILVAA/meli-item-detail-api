package com.meli.itemdetail.core.domain.model;

public class ProductSeller {
    private String storeName;
    private Integer totalSales;
    private String storeType;
    private Integer publishedProducts;

    public ProductSeller() {}

    public ProductSeller(String storeName, Integer totalSales, String storeType, Integer publishedProducts) {
        this.storeName = storeName;
        this.totalSales = totalSales;
        this.storeType = storeType;
        this.publishedProducts = publishedProducts;
    }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public Integer getTotalSales() { return totalSales; }
    public void setTotalSales(Integer totalSales) { this.totalSales = totalSales; }

    public String getStoreType() { return storeType; }
    public void setStoreType(String storeType) { this.storeType = storeType; }

    public Integer getPublishedProducts() { return publishedProducts; }
    public void setPublishedProducts(Integer publishedProducts) { this.publishedProducts = publishedProducts; }
}
