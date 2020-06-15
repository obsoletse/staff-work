package com.linbin.system.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.service.IBaseService;
import com.linbin.utils.TransformUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;


/**
 * @ClassName IBaseServiceImpl
 * @Author linBin
 * @Date 2020/3/23 19:03
 * @Description ：service基类实现类
 */

@Service
@Transactional
public abstract class IBaseServiceImpl<T> implements IBaseService<T> {

    //提供一个抽象方法 当前类的子类需要提供具体实现类的 Dao
    public abstract IBaseDao getBaseDao();
    //提供一个抽象方法  当前类的子类需要提供 表名
    public abstract String getTableName();
    public abstract Class<T> getClassEntity();

    public String tableName;
    public Class<T> classEntity;
    {
        tableName = getTableName();
        classEntity = getClassEntity();
    }
    @Override
    public int add(T t , String filterField) {
        List<Object> list= new ArrayList<>();
        List<String> fieldList = Arrays.asList(StrUtil.splitToArray(filterField, ','));
        /*获取T类中的所有字段*/
        for (Field field : t.getClass().getDeclaredFields()){
            /*反射时访问私有变量*/
            field.setAccessible(true);
            try {
                if (filterField != null){
                    if (fieldList.contains(field.getName())){
                        continue;
                    }
                }
                list.add(field.get(t));
            }catch (IllegalArgumentException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return getBaseDao().add(tableName , list.toArray());
    }

    @Override
    public void delete(int id) {
        getBaseDao().delete(tableName, id);
    }

    @Override
    public void update(T t) {
        int id = 0;
        List<Object> list= new ArrayList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            String fieldName = "";
            field.setAccessible(true);//权限
            try {
                if ( field.get(t) == null ) {
                    continue;
                }
                if (("id").equals( field.getName()) ) {
                    id = (Integer) field.get(t);
                    continue ;
                }
                if (TransformUtils.isContain(field.getName())){
                    fieldName = TransformUtils.trans(field.getName());
                }else {
                    fieldName = field.getName();
                }
                //拼接成 ：变量名='值' 的形式
                list.add( fieldName+"="+ "'" + field.get(t) + "'" );
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        getBaseDao().update( id, tableName , list.toArray() );

    }

    @Override
    public List<T> queryAll() {
        List<T> ts = new ArrayList<>();
        List<HashMap<Object, Object>> list =  getBaseDao().queryAll(tableName);
        for (HashMap<Object, Object> hashMap : list) {
            ts.add(hashMapToEntity(hashMap));
        }
        return ts;
    }

    @Override
    public T queryOneById(int id) {
        HashMap<Object, Object> map =  getBaseDao().queryOneById(tableName,id).get(0);
        T t = hashMapToEntity(map);
        return t;
    }

    /**
     * 将HashMap 转成 实体类对象
     */
    private T hashMapToEntity( Map<Object, Object> map ) {
        T t = null;
        try {
            t = classEntity.newInstance();
            for (Field f : t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                f.set(t,map.get(TransformUtils.trans(f.getName())));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return t;
    }
}
