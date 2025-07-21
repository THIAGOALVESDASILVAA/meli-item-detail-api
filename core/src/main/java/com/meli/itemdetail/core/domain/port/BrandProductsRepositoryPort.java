package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.Product;
import java.util.List;

public interface BrandProductsRepositoryPort {
    List<Product> findByBrandName(String brandName);
}
