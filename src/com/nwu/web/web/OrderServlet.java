package com.nwu.web.web;

import com.nwu.web.pojo.Cart;
import com.nwu.web.pojo.User;
import com.nwu.web.service.OrderService;
import com.nwu.web.service.impl.OrderServiceImpl;
import com.nwu.web.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = user.getId();
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);
        req.setAttribute("orderId", orderId);
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
