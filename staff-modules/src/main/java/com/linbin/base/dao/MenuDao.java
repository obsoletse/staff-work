package com.linbin.base.dao;

import com.linbin.base.entity.Menu;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/21 10:56
 * @Description:
 */
@Mapper
public interface MenuDao extends IBaseDao {

    List<Menu> getMenusByUserId(Integer userId);

    List<Integer> getCheckMenu(Integer roleId);

    Menu queryOneByName(String menuName);

    List<Menu> getMenuLike(String menuLike);

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);
}
