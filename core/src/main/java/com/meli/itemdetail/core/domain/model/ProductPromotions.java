package com.meli.itemdetail.core.domain.model;

import java.util.List;

public class ProductPromotions {
    private List<Coupon> coupons;

    public ProductPromotions() {}

    public ProductPromotions(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Coupon> getCoupons() { return coupons; }
    public void setCoupons(List<Coupon> coupons) { this.coupons = coupons; }

    public static class Coupon {
        private String title;
        private String description;
        private String type;
        private String bank;

        public Coupon() {}

        public Coupon(String title, String description, String type, String bank) {
            this.title = title;
            this.description = description;
            this.type = type;
            this.bank = bank;
        }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getBank() { return bank; }
        public void setBank(String bank) { this.bank = bank; }
    }
}
