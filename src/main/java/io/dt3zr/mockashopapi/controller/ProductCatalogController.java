package io.dt3zr.mockashopapi.controller;

import io.dt3zr.mockashopapi.dto.ProductDto;
import io.dt3zr.mockashopapi.persistence.entity.Product;
import io.dt3zr.mockashopapi.persistence.entity.mapper.ProductMapper;
import io.dt3zr.mockashopapi.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
public class ProductCatalogController {
    private ProductCatalogService productCatalogService;
    private ProductMapper productMapper;

    @Autowired
    public ProductCatalogController(ProductCatalogService productCatalogService, ProductMapper productMapper) {
        this.productCatalogService = productCatalogService;
        this.productMapper = productMapper;
    }

    @GetMapping(value = "/product-catalog", produces = "application/json")
    public @ResponseBody Set<ProductDto> getProducts() {
        return productCatalogService.getProducts().stream().map(productMapper::toProductDto).collect(toSet());
    }

    @GetMapping(value = "/product-catalog/{productCode}", produces = "application/json")
    public @ResponseBody ProductDto getProduct(@PathVariable String productCode) {
        Product product = productCatalogService.getCheckedProductByCode(productCode);
        return productMapper.toProductDto(product);
    }

    @PostMapping(value = "/product-catalog", consumes = "application/json", produces = "application/json")
    public void addProduct(@RequestBody ProductDto productDto) {
        productCatalogService.addProduct(productMapper.fromProductDto(productDto));
    }

}
