package com.nwu.web.service.impl;

import com.nwu.web.dao.BookDao;
import com.nwu.web.dao.OrderDao;
import com.nwu.web.dao.OrderItemDao;
import com.nwu.web.dao.impl.BookDaoImpl;
import com.nwu.web.dao.impl.OrderDaoImpl;
import com.nwu.web.dao.impl.OrderItemDaoImpl;
import com.nwu.web.pojo.*;
import com.nwu.web.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + String.valueOf(userId);
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        Map<Integer, CartItem> items = cart.getItems();
        for(Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
