package com.nwu.web.test;

import com.nwu.web.pojo.Cart;
import com.nwu.web.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(3, "3", 1, new BigDecimal(4), new BigDecimal(5)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(3, "3", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(3, "3", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(3, "3", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.clear();
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.updateCount(1, 10);
        System.out.println(cart);
    }
}