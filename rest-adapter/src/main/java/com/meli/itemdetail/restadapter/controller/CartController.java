package com.meli.itemdetail.restadapter.controller;

import com.meli.itemdetail.app.service.CartService;
import com.meli.itemdetail.restadapter.dto.AddToCartRequest;
import jakarta.validation.Valid;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@Valid @RequestBody AddToCartRequest request) {
        setMDCContext(request.getProductId());
        cartService.addToCart(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    private void setMDCContext(String productId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("productId", productId);
    }
}
