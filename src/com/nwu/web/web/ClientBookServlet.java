package com.nwu.web.web;

import com.nwu.web.pojo.Book;
import com.nwu.web.pojo.Page;
import com.nwu.web.service.BookService;
import com.nwu.web.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo") == null || req.getParameter("pageNo") == "" ? "1" : req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize") == null || req.getParameter("pageSize") == "" ? String.valueOf(Page.PAGE_SIZE) : req.getParameter("pageSize"));
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNO = Integer.parseInt(req.getParameter("pageNo") == null || req.getParameter("pageNo") == "" ? "1" : req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize") == null || req.getParameter("pageSize") == "" ? String.valueOf(Page.PAGE_SIZE) : req.getParameter("pageSize"));
        int min = Integer.parseInt(req.getParameter("min") == null || req.getParameter("min") == "" ? "0" : req.getParameter("min"));
        int max = Integer.parseInt(req.getParameter("max") == null || req.getParameter("max") == "" ? String.valueOf(Integer.MAX_VALUE) : req.getParameter("max"));
        Page<Book> page = bookService.pageByPrice(pageNO, pageSize, min, max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
