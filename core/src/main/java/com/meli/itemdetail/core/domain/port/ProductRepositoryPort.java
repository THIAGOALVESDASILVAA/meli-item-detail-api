package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Optional<Product> findById(String id);

    List<Product> findByBrand(String brand);
}
