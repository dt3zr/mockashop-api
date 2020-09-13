package io.dt3zr.mockashopapi.service.impl;

import io.dt3zr.mockashopapi.exception.ProductExistsException;
import io.dt3zr.mockashopapi.persistence.ProductCatalogRepository;
import io.dt3zr.mockashopapi.persistence.entity.Product;
import io.dt3zr.mockashopapi.persistence.entity.mapper.ProductMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductCatalogServiceImplTests {

    private List<Product> generateProductList() {
        Product p1 = new Product();
        Product p2 = new Product();
        p1.setCode("1");
        p2.setCode("2");
        return Arrays.asList(p1, p2);
    }

    @Test
    public void testGetProduct() {
        List<Product> productsInStore = generateProductList();
        ProductCatalogRepository productCatalogRepository = mock(ProductCatalogRepository.class);
        when(productCatalogRepository.findAll()).thenReturn(productsInStore);

        ProductCatalogServiceImpl service = new ProductCatalogServiceImpl(
                productCatalogRepository,
                new ProductMapperImpl());

        Set<Product> products = service.getProducts();

        assertEquals(products.size(), productsInStore.size());
    }

    @Test
    public void testAddDuplicatedProduct() {
        ProductCatalogRepository productCatalogRepository = mock(ProductCatalogRepository.class);
        when(productCatalogRepository.findByCode(any())).thenReturn(Optional.of(new Product()));

        ProductCatalogServiceImpl service = new ProductCatalogServiceImpl(
                productCatalogRepository,
                new ProductMapperImpl());

        assertThrows(ProductExistsException.class, () -> service.addProduct(new Product()));
    }

    @Test
    public void testAddProduct() {
        ProductCatalogRepository productCatalogRepository = mock(ProductCatalogRepository.class);
        when(productCatalogRepository.findByCode(any())).thenReturn(Optional.empty());

        ProductCatalogServiceImpl service = new ProductCatalogServiceImpl(
                productCatalogRepository,
                new ProductMapperImpl());

        service.addProduct(new Product());

        verify(productCatalogRepository, times(1)).save(any());
    }
}
