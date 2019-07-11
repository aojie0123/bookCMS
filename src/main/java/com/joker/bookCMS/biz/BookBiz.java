package com.joker.bookCMS.biz;

import com.joker.bookCMS.entity.Book;

import java.util.List;

public interface BookBiz {
    List<Book> getList();
    void batchInsert(List<Book> list);
}
