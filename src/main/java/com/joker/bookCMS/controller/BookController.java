package com.joker.bookCMS.controller;

import com.joker.bookCMS.biz.BookBiz;
import com.joker.bookCMS.biz.CategoryBiz;
import com.joker.bookCMS.biz.impl.BookBizImpl;
import com.joker.bookCMS.biz.impl.CategoryBizImpl;
import com.joker.bookCMS.entity.Book;
import com.joker.bookCMS.entity.Category;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BookController {

    private CategoryBiz categoryBiz = new CategoryBizImpl();
    private BookBiz bookBiz = new BookBizImpl();

    //  /admin/Book/list.do
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> list = bookBiz.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
    }

    //  /admin/Book/toAdd.do
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list = categoryBiz.getList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/admin/add_book.jsp").forward(request, response);
    }

    //  /admin/Book/add.do
    public void add(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, UnsupportedEncodingException {
        List<String> name = new ArrayList<>();
        List<String> cid = new ArrayList<>();
        List<String> level = new ArrayList<>();
        List<String> price = new ArrayList<>();
        List<String> imgPath = new ArrayList<>();

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(request);
        for (FileItem item : list) {
            if (item.isFormField()) {
                switch (item.getFieldName()) {
                    case "name":
                        name.add(item.getString("UTF-8"));
                        break;
                    case "categoryId":
                        cid.add(item.getString("UTF-8"));
                        break;
                    case "level":
                        level.add(item.getString("UTF-8"));
                        break;
                    case "price":
                        price.add(item.getString("UTF-8"));
                        break;
                }
            } else {
                if (item.getFieldName().equals("smallImg")) {
                    if (item.getSize() <= 100) {
                        imgPath.add(" ");
                        continue;
                    } else {
                        String rootPath = "";
                        String path = item.getName();
                        String type = ".jpg";
                        if (path.indexOf(".") != -1) {
                            type = path.substring(path.lastIndexOf("."));
                        }
                        path = "/download/images/" + System.currentTimeMillis() + type;
                        try {
                            item.write(new File(rootPath + path));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
