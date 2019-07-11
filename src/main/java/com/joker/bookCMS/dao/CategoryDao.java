package com.joker.bookCMS.dao;

import com.joker.bookCMS.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryDao {
    @Select("SELECT * FROM category")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<Category> findAll();

    @Insert("INSERT INTO category (name, create_time, update_time) VALUE (#{name}, #{createTime}, #{updateTime})")
    void insert(Category category);
}
