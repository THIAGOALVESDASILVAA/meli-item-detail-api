package com.meli.itemdetail.core.domain.model;

import java.util.List;

public class ProductDescription {
    private List<DescriptionBlock> blocks;

    public ProductDescription() {}

    public ProductDescription(List<DescriptionBlock> blocks) {
        this.blocks = blocks;
    }

    public List<DescriptionBlock> getBlocks() { return blocks; }
    public void setBlocks(List<DescriptionBlock> blocks) { this.blocks = blocks; }

    public static class DescriptionBlock {
        private String title;
        private String content;
        private String type;

        public DescriptionBlock() {}

        public DescriptionBlock(String title, String content, String type) {
            this.title = title;
            this.content = content;
            this.type = type;
        }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    }
}