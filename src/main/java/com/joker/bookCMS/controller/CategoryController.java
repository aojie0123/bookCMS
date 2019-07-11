package com.joker.bookCMS.controller;

import com.joker.bookCMS.biz.CategoryBiz;
import com.joker.bookCMS.biz.impl.CategoryBizImpl;
import com.joker.bookCMS.entity.Category;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CategoryController {
    private CategoryBiz categoryBiz = new CategoryBizImpl();

    //  /admin/Category//toAdd.do
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/add_category.jsp").forward(request, response);
    }
    //  /admin/Category//add.do
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Timestamp createTime = new Timestamp(new Date().getTime());
        Timestamp updateTime = new Timestamp(new Date().getTime());

        Category category = new Category();
        category.setName(name);
        category.setCreateTime(createTime);
        category.setUpdateTime(updateTime);

        categoryBiz.insert(category);
        request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
    }
}
