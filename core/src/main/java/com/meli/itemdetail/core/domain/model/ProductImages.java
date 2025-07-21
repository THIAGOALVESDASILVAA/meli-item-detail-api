package com.meli.itemdetail.core.domain.model;

import java.util.List;

public class ProductImages {
    private List<String> images;

    public ProductImages() {}

    public ProductImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
}
