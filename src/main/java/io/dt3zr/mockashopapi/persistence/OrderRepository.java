package io.dt3zr.mockashopapi.persistence;

import io.dt3zr.mockashopapi.persistence.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Set<Order> findOrderByUsername(String username);
    Optional<Order> findByTransactionId(String transactionId);
}
