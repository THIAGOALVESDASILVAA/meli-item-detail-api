package com.meli.itemdetail.core.domain.model;

public class ProductShipping {
    private Boolean freeShipping;
    private Integer estimatedDeliveryDays;
    private String origin;
    private String carrier;

    public ProductShipping() {}

    public ProductShipping(Boolean freeShipping, Integer estimatedDeliveryDays, String origin, String carrier) {
        this.freeShipping = freeShipping;
        this.estimatedDeliveryDays = estimatedDeliveryDays;
        this.origin = origin;
        this.carrier = carrier;
    }

    public Boolean getFreeShipping() { return freeShipping; }
    public void setFreeShipping(Boolean freeShipping) { this.freeShipping = freeShipping; }

    public Integer getEstimatedDeliveryDays() { return estimatedDeliveryDays; }
    public void setEstimatedDeliveryDays(Integer estimatedDeliveryDays) { this.estimatedDeliveryDays = estimatedDeliveryDays; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }
}