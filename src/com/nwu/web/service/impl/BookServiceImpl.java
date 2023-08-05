package com.nwu.web.service.impl;

import com.nwu.web.dao.BookDao;
import com.nwu.web.dao.impl.BookDaoImpl;
import com.nwu.web.pojo.Book;
import com.nwu.web.pojo.Page;
import com.nwu.web.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBook();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        Integer pageTotal = pageTotalCount / pageSize + (pageTotalCount % pageSize > 0 ? 1 : 0);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int begin = (page.getPageNo() - 1) * page.getPageSize();
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        Integer pageTotal = pageTotalCount / pageSize + (pageTotalCount % pageSize > 0 ? 1 : 0);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int begin = (page.getPageNo() - 1) * page.getPageSize();
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);
        return page;
    }
}
