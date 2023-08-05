package com.nwu.web.test;

import com.nwu.web.pojo.Cart;
import com.nwu.web.pojo.CartItem;
import com.nwu.web.service.OrderService;
import com.nwu.web.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(1, "2", 1, new BigDecimal(4), new BigDecimal(5)));
        cart.addItem(new CartItem(3, "3", 1, new BigDecimal(4), new BigDecimal(5)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println(orderService.createOrder(cart, 1));
    }
}