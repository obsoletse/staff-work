package com.linbin.base.service.impl;

import com.linbin.base.dao.MenuDao;
import com.linbin.base.entity.Menu;
import com.linbin.base.service.MenuService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/21 14:54
 * @Description:
 */
@Service
@Transactional
public class MenuServiceImpl extends IBaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenusByUserId(Integer userId) {
        return menuDao.getMenusByUserId(userId);
    }

    @Override
    public List<Integer> getCheckMenu(Integer roleId) {
        return menuDao.getCheckMenu(roleId);
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuDao.getAllMenus();
    }

    @Override
    public Menu queryOneByName(String menuName) {
        return menuDao.queryOneByName(menuName);
    }


    @Override
    public List<Menu> getMenuLike(String query) {
        String menuLike = "%" + query + "%";
        return menuDao.getMenuLike(menuLike);
    }

    @Override
    public IBaseDao getBaseDao() {
        return menuDao;
    }

    @Override
    public String getTableName() {
        return "sys_menu";
    }

    @Override
    public Class<Menu> getClassEntity() {
        return Menu.class;
    }

}
