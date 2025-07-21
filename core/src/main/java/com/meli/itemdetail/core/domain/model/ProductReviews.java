package com.meli.itemdetail.core.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class ProductReviews {
    private BigDecimal averageRating;
    private Integer reviewCount;
    private List<Comment> comments;

    public ProductReviews() {}

    public ProductReviews(BigDecimal averageRating, Integer reviewCount, List<Comment> comments) {
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.comments = comments;
    }

    public BigDecimal getAverageRating() { return averageRating; }
    public void setAverageRating(BigDecimal averageRating) { this.averageRating = averageRating; }

    public Integer getReviewCount() { return reviewCount; }
    public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public static class Comment {
        private String author;
        private Integer rating;
        private String text;
        private String date;

        public Comment() {}

        public Comment(String author, Integer rating, String text, String date) {
            this.author = author;
            this.rating = rating;
            this.text = text;
            this.date = date;
        }

        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }

        public Integer getRating() { return rating; }
        public void setRating(Integer rating) { this.rating = rating; }

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
    }
}

