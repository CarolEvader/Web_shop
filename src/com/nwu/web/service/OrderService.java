package com.nwu.web.service;

import com.nwu.web.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
