package com.linbin.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IBaseDao
 * @Author linBin
 * @Date 2020/3/23 19:08
 * @Description ：
 */
@Mapper
public interface IBaseDao{

    int add( @Param("tableName")String tableName ,@Param("params") Object ...params );

    void delete( @Param("tableName")String tableName, @Param("id") int id);

    void update( @Param("id") int id, @Param("tableName") String tableName, @Param("params")Object []params );

    List<HashMap<Object, Object>> queryOneById(@Param("tableName") String tableName, @Param("id") int id );

    List<HashMap<Object, Object>> queryAll( @Param("tableName") String tableName );

    List<HashMap<Object, Object>> queryByPage(
            @Param("tableName")String tableName,
            @Param("page")int page,
            @Param("pageSize")int pageSize,
            String where);

    int queryCount( @Param("tableName")String tableName , @Param("where") String where);

}
