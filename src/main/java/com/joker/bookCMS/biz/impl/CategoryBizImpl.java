package com.joker.bookCMS.biz.impl;

import com.joker.bookCMS.biz.CategoryBiz;
import com.joker.bookCMS.dao.CategoryDao;
import com.joker.bookCMS.entity.Category;
import com.joker.bookCMS.global.DaoFactory;

import java.util.List;

public class CategoryBizImpl implements CategoryBiz {

    private CategoryDao categoryDao = DaoFactory.getInstance().getDao(CategoryDao.class);

    @Override
    public List<Category> getList() {
        return categoryDao.findAll();
    }

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }
}
