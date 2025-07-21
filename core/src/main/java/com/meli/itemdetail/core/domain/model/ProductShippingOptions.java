package com.meli.itemdetail.core.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class ProductShippingOptions {
    private final Boolean freeShipping;
    private final String originZipCode;
    private final List<ShippingOption> options;

    public ProductShippingOptions(Boolean freeShipping, String originZipCode, List<ShippingOption> options) {
        this.freeShipping = freeShipping;
        this.originZipCode = originZipCode;
        this.options = options;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public String getOriginZipCode() {
        return originZipCode;
    }

    public List<ShippingOption> getOptions() {
        return options;
    }

    public static class ShippingOption {
        private final String type;
        private final BigDecimal price;
        private final Integer deliveryDays;

        public ShippingOption(String type, BigDecimal price, Integer deliveryDays) {
            this.type = type;
            this.price = price;
            this.deliveryDays = deliveryDays;
        }

        public String getType() {
            return type;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public Integer getDeliveryDays() {
            return deliveryDays;
        }
    }
}

