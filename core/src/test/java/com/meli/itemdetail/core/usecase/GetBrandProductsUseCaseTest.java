package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.domain.port.BrandProductsRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetBrandProductsUseCaseTest {

    @Mock
    private BrandProductsRepositoryPort repository;

    private GetBrandProductsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetBrandProductsUseCase(repository);
    }

    @Test
    void shouldReturnProductsWhenBrandExists() {
        String brand = "Samsung";
        List<Product> expectedProducts = Arrays.asList(
                createProduct("1", "Samsung Galaxy S23", new BigDecimal("2899.99")),
                createProduct("2", "Samsung Galaxy A54", new BigDecimal("1299.99"))
        );
        when(repository.findByBrandName(brand)).thenReturn(expectedProducts);

        List<Product> result = useCase.execute(brand);

        assertEquals(2, result.size());
        assertEquals(expectedProducts, result);
        verify(repository).findByBrandName(brand);
    }

    @Test
    void shouldReturnEmptyListWhenBrandNotExists() {
        String brand = "NonExistentBrand";
        when(repository.findByBrandName(brand)).thenReturn(Collections.emptyList());

        List<Product> result = useCase.execute(brand);

        assertTrue(result.isEmpty());
        verify(repository).findByBrandName(brand);
    }

    private Product createProduct(String id, String title, BigDecimal price) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        return product;
    }
}
