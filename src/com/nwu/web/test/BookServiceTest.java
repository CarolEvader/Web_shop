package com.nwu.web.test;

import com.nwu.web.pojo.Book;
import com.nwu.web.pojo.Page;
import com.nwu.web.service.BookService;
import com.nwu.web.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "1", "1", new BigDecimal(999), 100, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
        bookService.deleteBookById(23);
        bookService.deleteBookById(24);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(27, "111", "1", new BigDecimal(999), 100, 100, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookService.queryBooks());
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByService() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50));
    }

}