package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.domain.port.BrandProductsRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonBrandProductsRepositoryAdapter implements BrandProductsRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, List<Product>> brandCache = new ConcurrentHashMap<>();

    public JsonBrandProductsRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/brand-products.json");
            var brandsList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            for (Map<String, Object> brandData : brandsList) {
                String brand = (String) brandData.get("brand");
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> productsData = (List<Map<String, Object>>) brandData.get("products");
                
                List<Product> products = productsData.stream()
                        .map(this::mapToProduct)
                        .collect(Collectors.toList());
                
                brandCache.put(brand, products);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load brand products data", e);
        }
    }

    @Override
    public List<Product> findByBrandName(String brandName) {
        return brandCache.getOrDefault(brandName, List.of());
    }

    private Product mapToProduct(Map<String, Object> data) {
        var product = new Product();
        product.setId((String) data.get("id"));
        product.setTitle((String) data.get("title"));
        Object priceObj = data.get("price");
        BigDecimal price = priceObj instanceof Number ? new BigDecimal(priceObj.toString()) : null;
        product.setPrice(price);
        product.setCategory((String) data.get("category"));
        return product;
    }
}
