package com.linbin.base.service;

import com.linbin.base.entity.Menu;
import com.linbin.system.service.IBaseService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/21 14:53
 * @Description:
 */
public interface MenuService extends IBaseService<Menu> {
    List<Menu> getMenusByUserId(Integer userId);
    List<Integer> getCheckMenu(Integer roleId);
    List<Menu> getAllMenu();
    Menu queryOneByName(String menuName);
    List<Menu> getMenuLike (String query);
}
