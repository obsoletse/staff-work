package com.linbin.base.controller;

import com.linbin.base.entity.Role;
import com.linbin.base.entity.UserRole;
import com.linbin.base.service.RoleMenuService;
import com.linbin.base.service.RoleService;
import com.linbin.base.service.UserRoleService;
import com.linbin.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 16:57
 * @Description:
 */
@RestController
@RequestMapping("/role")
@Transactional
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/getAllRole")
    public Result<List<Role>> getAllRole(){
        Result<List<Role>> res = new Result<>();
        List<Role> roles = roleService.queryAll();
        res.setResult(roles);
        return res.success("获取成功！");
    }

    @GetMapping("/getUserRole")
    public Result<List<String>> getUserRole(@RequestParam Integer userId){
        Result<List<String>> res = new Result<>();
        List<String> roles = roleService.queryRoleByUserId(userId);
        res.setResult(roles);
        return res.success("获取成功！");
    }

    @PostMapping("/updateUserRole")
    public Result updateUserRole(@RequestBody HashMap map){
        List<String> roles = (List<String>) map.get("roles");
        Integer userId = (Integer) map.get("userId");
        userRoleService.deleteRoleByUserId(userId);
        for (String roleName : roles){
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleService.queryRoleIdByName(roleName));
            userRole.setUserId(userId);
            userRoleService.add(userRole,null);
        }
        return Result.ok();
    }

    @GetMapping("/getRoleList")
    public Result<HashMap> getUserList(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        Result<HashMap> res = new Result<>();
        List<Role> roleList = roleService.queryRoleList(page,pageSize);
        Integer total = roleService.countTotal();
        HashMap map = new HashMap();
        map.put("roleList",roleList);
        map.put("total",total);
        res.setResult(map);
        return res.success("获取角色列表成功！");
    }

    @GetMapping("/getLikeRoles")
    public Result<HashMap> getLikeRoles(@RequestParam("roleLike") String roleLike , @RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        Result<HashMap> res = new Result<>();
        List<Role> roleList = roleService.getLikeRoles("%" + roleLike + "%" , page ,pageSize);
        HashMap map = new HashMap();
        map.put("roleList",roleList);
        map.put("total",roleService.countRoleLikeTotal("%" + roleLike + "%"));
        res.setResult(map);
        return res.success("获取角色列表成功！");
    }

    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role){
        Result res = new Result();
        if (roleService.queryOneByCode(role.getCode()) != null){
            return res.error500("此角色编码已经存在！");
        }
        if (roleService.queryOneByName(role.getName()) != null){
            return res.error500("此角色名已经存在！");
        }
        roleService.add(role,null);
        return res.success("角色添加成功！");
    }

    @PostMapping("/updateRole")
    public Result updateUser(@RequestBody Role role){
        roleService.update(role);
        return Result.ok("修改成功！");
    }

    @PostMapping("/delSelectRole")
    public Result delSelectRole(@RequestBody List<Role> delRoleList){
        for (Role role : delRoleList){
            roleService.delete(role.getId());
            roleMenuService.deleteByRoleId(role.getId());
            userRoleService.deleteByRoleId(role.getId());
        }
        return Result.ok();
    }

    @DeleteMapping("/delOneRole")
    public Result delOneRole(@RequestParam Integer roleId){
        roleService.delete(roleId);
        roleMenuService.deleteByRoleId(roleId);
        userRoleService.deleteByRoleId(roleId);
        return Result.ok();
    }
}
