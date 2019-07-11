package com.joker.bookCMS.biz;

import com.joker.bookCMS.entity.Category;

import java.util.List;

public interface CategoryBiz {
    List<Category> getList();
    void insert(Category category);
}
