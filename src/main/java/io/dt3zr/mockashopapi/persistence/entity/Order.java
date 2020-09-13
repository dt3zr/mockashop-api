package io.dt3zr.mockashopapi.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
@Table(name = "purchase_order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(unique = true)
    private String transactionId;
    private Date transactionDate;
    private String status;
    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    @Override
    public String toString() {
        Set<String> items = orderItems.stream()
                .map(o -> "{" + o.getProductCode() + ", " + o.getQuantity() + ", " + o.getPrice() + "}")
                .collect(toSet());
        return "Order{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", username='" + username + '\'' +
                ", orderItems=" + items +
                '}';
    }
}
