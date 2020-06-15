package com.linbin.base.controller;

import com.linbin.base.entity.Menu;
import com.linbin.base.entity.Role;
import com.linbin.base.entity.RoleMenu;
import com.linbin.base.service.MenuService;
import com.linbin.base.service.RoleMenuService;
import com.linbin.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/21 10:55
 * @Description: 动态加载前端菜单
 */
@RestController
@RequestMapping("/menu")
@Transactional
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping("/getMenuList")
    public Result<List<Menu>> getMenuList(@RequestParam Integer userId){
        Result<List<Menu>> res = new Result<>();
        List<Menu> menuList = menuService.getMenusByUserId(userId);
        if (menuList == null){
            return res.error500("服务器异常！");
        }
        res.setResult(menuList);
        log.info("----------菜单信息如下----------");
        log.info(menuList.toString());
        return res.success("获取菜单成功！");
    }

    @GetMapping("/getCheckMenu")
    public Result<List<Integer>> getCheckMenu(@RequestParam Integer roleId){
        Result<List<Integer>> res = new Result<>();
        List<Integer> checkMenuList = menuService.getCheckMenu(roleId);
        res.setResult(checkMenuList);
        log.info("----------角色所属菜单信息如下----------");
        log.info(checkMenuList.toString());
        return res.success("获取菜单成功！");
    }

    @GetMapping("/getAllMenu")
    public Result<List<Menu>> getAllMenu(){
        Result<List<Menu>> res = new Result<>();
        List<Menu> menuList = menuService.getAllMenu();
        res.setResult(menuList);
        log.info("----------角色总体菜单信息如下----------");
        log.info(menuList.toString());
        return res.success("获取菜单成功！");
    }

    @PostMapping("/updateRoleMenu")
    public Result updateRoleMenu(@RequestBody HashMap map){
        List<Integer> menuIdList = (List<Integer>) map.get("keys");
        Integer roleId = (Integer) map.get("roleId");
        roleMenuService.deleteByRoleId(roleId);
        for (Integer menuId : menuIdList){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenuService.add(roleMenu,null);
        }
        return Result.ok();
    }

    @GetMapping("/getMenuById")
    public Result<Menu> getMenuById(@RequestParam Integer menuId){
        Result<Menu> res = new Result<>();
        Menu menu = menuService.queryOneById(menuId);
        res.setResult(menu);
        return res.success("获取成功！");
    }

    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu){
        menuService.update(menu);
        return Result.ok();
    }

    @PostMapping("/addMenu")
    public Result addMenu(@RequestBody Menu menu){
        Result res = new Result();
        if (menuService.queryOneByName(menu.getName()) != null){
            return res.error500("此菜单已存在！");
        }
        menuService.add(menu,"children");
        return res.success("菜单添加成功！");
    }

    @GetMapping("/getMenuLike")
    public Result<List<Menu>> getMenuLike(@RequestParam String query){
        Result<List<Menu>> res = new Result<>();
        List<Menu> menuList = menuService.getMenuLike(query);
        res.setResult(menuList);
        return res.success("获取成功！");
    }

    @PostMapping("/delSelectMenu")
    public Result delSelectMenu (@RequestBody List<Integer> idList){
        for (Integer menuId: idList) {
            roleMenuService.deleteByMenuId(menuId);
            menuService.delete(menuId);
        }
        return Result.ok();
    }
}
