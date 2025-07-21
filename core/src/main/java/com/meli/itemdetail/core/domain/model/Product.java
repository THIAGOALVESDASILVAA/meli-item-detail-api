package com.meli.itemdetail.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private String id;
    private String title;
    private BigDecimal price;
    private String condition;
    private Boolean freeShipping;
    private LocalDateTime created;
    private String category;
    private String shortDescription;

    public Product() {}

    public Product(String id, String title, BigDecimal price, String condition, Boolean freeShipping,
                   LocalDateTime created, String category, String shortDescription) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.condition = condition;
        this.freeShipping = freeShipping;
        this.created = created;
        this.category = category;
        this.shortDescription = shortDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
