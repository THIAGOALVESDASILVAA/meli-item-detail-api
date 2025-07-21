package com.meli.itemdetail.restadapter.controller;

import com.meli.itemdetail.app.service.BrandProductsService;
import com.meli.itemdetail.core.domain.model.Product;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandProductsService brandProductsService;

    public BrandController(BrandProductsService brandProductsService) {
        this.brandProductsService = brandProductsService;
    }

    @GetMapping("/{brand}/products")
    public ResponseEntity<List<Product>> getBrandProducts(@PathVariable String brand) {
        setMDCContext(brand);
        return ResponseEntity.ok(brandProductsService.getBrandProducts(brand));
    }

    private void setMDCContext(String brand) {
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("brand", brand);
    }
}
