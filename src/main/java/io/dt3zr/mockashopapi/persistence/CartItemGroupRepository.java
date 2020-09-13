package io.dt3zr.mockashopapi.persistence;

import io.dt3zr.mockashopapi.persistence.entity.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemGroupRepository extends CrudRepository<CartItem, Long> {
}
