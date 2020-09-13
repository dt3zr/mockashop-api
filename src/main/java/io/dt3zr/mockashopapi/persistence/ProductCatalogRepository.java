package io.dt3zr.mockashopapi.persistence;

import io.dt3zr.mockashopapi.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCatalogRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);
}
