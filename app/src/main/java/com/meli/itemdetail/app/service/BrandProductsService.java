package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.usecase.GetBrandProductsUseCase;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BrandProductsService {

    private final GetBrandProductsUseCase getBrandProductsUseCase;

    public BrandProductsService(GetBrandProductsUseCase getBrandProductsUseCase) {
        this.getBrandProductsUseCase = getBrandProductsUseCase;
    }

    public List<Product> getBrandProducts(String brandName) {
        return getBrandProductsUseCase.execute(brandName);
    }
}
