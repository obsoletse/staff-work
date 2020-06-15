package com.linbin.system.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;;

import java.util.List;

/**
 * @ClassName IBaseService
 * @Author linBin
 * @Date 2020/3/23 18:59
 * @Description ：service基类
 */
public interface IBaseService<T> {
    /**
     * 基类增加方法
     * @param t
     * @return
     */
    int add(T t , String filterField);

    /**
     * 基类删除方法
     * @param id
     */
    void delete( int id );

    /**
     * 基类修改方法
     * @param t
     */
    void update(T t);

    /**
     * 基类全部查询方法
     * @return
     */
    List<T> queryAll();

    /**
     * 基类单个查询方法--通过id
     * @param id
     * @return
     */
    T queryOneById(int id);
}
