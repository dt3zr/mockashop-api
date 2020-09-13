package io.dt3zr.mockashopapi.service.impl;

import io.dt3zr.mockashopapi.exception.ProductExistsException;
import io.dt3zr.mockashopapi.exception.ProductNotFoundException;
import io.dt3zr.mockashopapi.persistence.ProductCatalogRepository;
import io.dt3zr.mockashopapi.persistence.entity.Product;
import io.dt3zr.mockashopapi.persistence.entity.mapper.ProductMapper;
import io.dt3zr.mockashopapi.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {
    private ProductCatalogRepository productCatalogRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductCatalogServiceImpl(ProductCatalogRepository productCatalogRepository, ProductMapper productMapper) {
        this.productCatalogRepository = productCatalogRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Set<Product> getProducts() {
        return productCatalogRepository.findAll().stream().collect(toSet());
    }

    @Override
    public Optional<Product> getProductByCode(String productCode) {
        return productCatalogRepository.findByCode(productCode);
    }

    @Override
    public Product getCheckedProductByCode(String productCode) {
        return getProductByCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with code '%s' cannot be found", productCode)));
    }

    @Override
    public void addProduct(Product product) {
        productCatalogRepository.findByCode(product.getCode()).ifPresent(p -> {
            throw new ProductExistsException(String.format("Cannot add product with code '%s'. Product exists.", p.getCode()));
        });
        productCatalogRepository.save(product);
    }

}
