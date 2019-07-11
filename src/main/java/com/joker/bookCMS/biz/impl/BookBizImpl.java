package com.joker.bookCMS.biz.impl;

import com.joker.bookCMS.biz.BookBiz;
import com.joker.bookCMS.dao.BookDao;
import com.joker.bookCMS.entity.Book;
import com.joker.bookCMS.global.DaoFactory;

import java.util.List;

public class BookBizImpl implements BookBiz {

    private BookDao bookDao = DaoFactory.getInstance().getDao(BookDao.class);

    @Override
    public List<Book> getList() {
        return bookDao.findAll();
    }

    @Override
    public void batchInsert(List<Book> list) {
        bookDao.batchInsert(list);
    }
}
