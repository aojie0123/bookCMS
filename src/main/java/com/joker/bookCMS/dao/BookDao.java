package com.joker.bookCMS.dao;

import com.joker.bookCMS.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookDao {
    @Select("SELECT b.*, c.name cname FROM book b LEFT JOIN category c ON b.category_id = c.id ORDER BY update_time DESC")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "category_id", property = "cid"),
            @Result(column = "name", property = "name"),
            @Result(column = "level", property = "level"),
            @Result(column = "price", property = "price"),
            @Result(column = "img_path", property = "imgPath"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "cname", property = "category.name")
    })
    List<Book> findAll();

    @Insert("<script>" +
            "INSERT INTO book (category_id, name, level, price, img_path, create_time, update_time) VALUES " +
            "<foreach collection='list' item='book' separator=','>" +
            "(#{book.cid}, #{book.name}, #{book.level}, #{book.price}, #{book.imgPath}, #{book.createTime}, #{book.updateTime})" +
            "</foreach>" +
            "</script>")
    void batchInsert(List<Book> list);
}
