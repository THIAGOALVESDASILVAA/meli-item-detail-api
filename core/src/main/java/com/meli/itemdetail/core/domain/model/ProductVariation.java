package com.meli.itemdetail.core.domain.model;

import java.util.List;

public class ProductVariation {

    private List<String> availableColors;
    private List<String> storageOptions;
    private List<String> ramOptions;

    public ProductVariation() {}

    public ProductVariation(List<String> availableColors, List<String> storageOptions, List<String> ramOptions) {
        this.availableColors = availableColors;
        this.storageOptions = storageOptions;
        this.ramOptions = ramOptions;
    }

    public List<String> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<String> availableColors) {
        this.availableColors = availableColors;
    }

    public List<String> getStorageOptions() {
        return storageOptions;
    }

    public void setStorageOptions(List<String> storageOptions) {
        this.storageOptions = storageOptions;
    }

    public List<String> getRamOptions() {
        return ramOptions;
    }

    public void setRamOptions(List<String> ramOptions) {
        this.ramOptions = ramOptions;
    }
}
