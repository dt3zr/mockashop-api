package io.dt3zr.mockashopapi.service;

import io.dt3zr.mockashopapi.persistence.entity.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductCatalogService {
    Set<Product> getProducts();
    Optional<Product> getProductByCode(String productCode);
    Product getCheckedProductByCode(String productCode);
    void addProduct(Product product);
}
