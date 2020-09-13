package io.dt3zr.mockashopapi.persistence;

import io.dt3zr.mockashopapi.persistence.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUsername(String username);
}
