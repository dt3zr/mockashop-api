package io.dt3zr.mockashopapi.service.impl;

import io.dt3zr.mockashopapi.exception.ShoppingCartNotFoundException;
import io.dt3zr.mockashopapi.persistence.CartItemGroupRepository;
import io.dt3zr.mockashopapi.persistence.ShoppingCartRepository;
import io.dt3zr.mockashopapi.persistence.entity.CartItem;
import io.dt3zr.mockashopapi.persistence.entity.ShoppingCart;
import io.dt3zr.mockashopapi.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
    private ShoppingCartRepository shoppingCartRepository;
    private CartItemGroupRepository cartItemGroupRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, CartItemGroupRepository cartItemGroupRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemGroupRepository = cartItemGroupRepository;
    }

    @Override
    public void addCartItem(String username, CartItem cartItem) {
        // TODO: basic validation of parameters

        log.info("Trying to add {}", cartItem);
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByUsername(username);

        if (shoppingCartOptional.isPresent()) {
            // find cart item that has the same product code and update
            // the quantity if it exists otherwise just add to the cart
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            Optional<CartItem> itemGroupOptional = shoppingCart
                    .getCartItems().stream()
                    .filter(g -> g.getProductCode().equals(cartItem.getProductCode()))
                    .findFirst();

            if (itemGroupOptional.isPresent()) {
                CartItem itemGroup = itemGroupOptional.get();
                log.info(String.format("Updating cart item group %s", itemGroup));
                itemGroup.setQuantity(itemGroup.getQuantity() + cartItem.getQuantity());
                log.info(String.format("Updated cart item group %s", itemGroup));
            } else {
                shoppingCart.getCartItems().add(cartItem);
                cartItem.setShoppingCart(shoppingCart);
                log.info(String.format("Adding new cart item group %s to %s", cartItem, shoppingCart));
            }

            shoppingCartRepository.save(shoppingCart);
        } else {
            log.info(String.format("No shopping cart found for %s", username));
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUsername(username);
            shoppingCart.addCartItemGroup(cartItem);
            log.info("Created new shopping cart {}", shoppingCart);
            log.info(String.format("Adding new cart item group %s to new shopping cart %s", cartItem, shoppingCart));
            shoppingCartRepository.save(shoppingCart);
        }

    }

    @Override
    public void clear(String username) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUsername(username)
                .orElseThrow(() -> new ShoppingCartNotFoundException(String.format("Shopping cart not found for %s", username)));
        shoppingCartRepository.deleteById(shoppingCart.getId());
    }

    @Override
    public Set<CartItem> getCartItemsByUsername(String username) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUsername(username)
                .orElseThrow(() -> new ShoppingCartNotFoundException(String.format("Shopping cart not found for %s", username)));
        return Collections.unmodifiableSet(shoppingCart.getCartItems());
    }
}
