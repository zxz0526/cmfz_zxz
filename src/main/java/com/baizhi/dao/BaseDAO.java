package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T> {

    T login(T t);
    List<T> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    Long queryTotal();
    void add(T t);
    void delete(String id);
    void update(T t);
    T queryById(String id);
    List<T> queryAll();
}
