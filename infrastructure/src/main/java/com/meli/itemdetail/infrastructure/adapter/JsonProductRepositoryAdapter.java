package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.domain.port.ProductRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonProductRepositoryAdapter implements ProductRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, Product> productCache = new ConcurrentHashMap<>();
    private final Map<String, List<Product>> brandCache = new ConcurrentHashMap<>();

    public JsonProductRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/products.json");
            var productsList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            var products = productsList.stream()
                    .map(this::mapToProduct)
                    .collect(Collectors.toList());

            productCache.putAll(products.stream()
                    .collect(Collectors.toMap(Product::getId, product -> product)));

            products.stream()
                    .filter(product -> product.getCategory() != null)
                    .collect(Collectors.groupingBy(Product::getCategory))
                    .forEach((category, productList) -> {
                        brandCache.put(category.toLowerCase(), productList);
                        // Também adicionar variações do nome da marca
                        if ("smartphones".equals(category.toLowerCase())) {
                            brandCache.put("samsung", productList.stream()
                                    .filter(p -> p.getTitle() != null && p.getTitle().toLowerCase().contains("samsung"))
                                    .collect(Collectors.toList()));
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException("Failed to load products data", e);
        }
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productCache.get(id));
    }

    @Override
    public List<Product> findByBrand(String brand) {
        return brandCache.getOrDefault(brand, Collections.emptyList());
    }

    private Product mapToProduct(Map<String, Object> data) {
        var product = new Product();
        product.setId((String) data.get("id"));
        product.setTitle((String) data.get("title"));
        Object priceObj = data.get("price");
        BigDecimal price = priceObj instanceof Number ? new BigDecimal(priceObj.toString()) : null;
        product.setPrice(price);
        product.setCondition((String) data.get("condition"));
        product.setFreeShipping((Boolean) data.get("free_shipping"));
        product.setCategory((String) data.get("category"));
        product.setShortDescription((String) data.get("short_description"));
        return product;
    }
}
