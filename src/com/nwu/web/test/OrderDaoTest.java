package com.nwu.web.test;

import com.nwu.web.dao.OrderDao;
import com.nwu.web.dao.impl.OrderDaoImpl;
import com.nwu.web.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));
    }
}