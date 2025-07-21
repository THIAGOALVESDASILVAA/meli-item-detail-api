package com.meli.itemdetail.core.domain.model;

public class ProductAvailability {
    private final Integer availableQuantity;
    private final Boolean available;
    private final String location;

    public ProductAvailability(Integer availableQuantity, Boolean available, String location) {
        this.availableQuantity = availableQuantity;
        this.available = available;
        this.location = location;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public Boolean getAvailable() {
        return available;
    }

    public String getLocation() {
        return location;
    }
}
