package com.linbin.base.controller;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserRoleService;
import com.linbin.base.service.UserService;
import com.linbin.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/3 12:15
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/isEnabled")
    public Result isEnabled(@RequestParam String username){
       User user = userService.queryOneByUserName(username);
       if (user.getIsEnabled() == 1){
           return Result.ok(true);
       }
       return Result.ok(false);
    }

    @GetMapping("/getUserList")
    public Result<HashMap> getUserList(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        Result<HashMap> res = new Result<>();
        logger.info("----------传入参数：page = " + page + ", pageSize = " + pageSize + "----------");
        List<User> userList = userService.queryUserList(page,pageSize);
        Integer total = userService.countTotal();
        HashMap map = new HashMap();
        map.put("userList",userList);
        map.put("total",total);
        logger.info("------用户列表获取如下：-----");
        logger.info(userList.toString());
        logger.info("------总条数：" + total + "条------");
        res.setResult(map);
        return res.success("获取用户列表成功！");
    };

    @GetMapping("/getUserAvatar")
    public Result<String> getUserAvatar(@RequestParam String username){
        Result<String> res = new Result<>();
        String avatarUrl = userService.queryAvatarUrl(username);
        res.setResult(avatarUrl);
        res.success("用户头像获取成功！");
        return res;
    }

    @GetMapping("/getUserInfo")
    public Result<User> getUserInfo(@RequestParam String username){
        Result<User> res = new Result<>();
        User user = userService.queryOneByUserName(username);
        if (user ==  null){
            return res.error500("获取用户失败！后台数据暂无响应！");
        }
        logger.info("----------用户信息获取如下:----------");
        logger.info(user.toString());
        res.setResult(user);
        return res.success("用户信息获取成功!");
    }

    @GetMapping("/getUserByName")
    public Result<User> getUserByName(@RequestParam String realName){
        Result<User> res = new Result<>();
        User user = userService.queryOneByRealName(realName);
        if (user ==  null){
            return res.error500("获取用户失败！后台数据暂无响应！");
        }
        logger.info("----------用户信息获取如下:----------");
        logger.info(user.toString());
        res.setResult(user);
        return res.success("用户信息获取成功!");
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        try {
            userService.update(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("服务器异常！请检查后台系统");
        }
        return Result.ok("修改成功！");
    }

    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody HashMap<String,String> map){
        String username = map.get("username");
        String newPwd = map.get("newPassword");
        String olePwd =  map.get("oldPassword");
        String phone = map.get("phone");
        User user = userService.queryOneByUserName(username);
        if (!user.getPhone().equals(phone)){
            return Result.error("用户绑定手机号不一致！");
        }
        userService.updatePwdByUserName(username,bCryptPasswordEncoder.encode(newPwd));
        return Result.ok();
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        Result res = new Result();
        if (userService.queryOneByUserName(user.getUsername()) != null){
            return res.error500("此用户名已被注册！");
        }
        if (userService.queryOneByPhone(user.getPhone()) != null){
            return res.error500("此手机号已经绑定过用户！");
        }
        Integer workNo = ((int)(Math.random() * 999999 + 1 ));
        String password = bCryptPasswordEncoder.encode("123456");
        /*设置工号和默认密码*/
        user.setWorkNo(workNo.toString());
        user.setPassword(password);
        try {
            userService.add(user,"depart,roles");
        }catch (Exception e){
            e.printStackTrace();
            return res.error500("服务器错误");
        }
        return res.success("用户添加成功！");
    }

    @PostMapping("/delSelectUser")
    public Result delSelectUser(@RequestBody List<User> delUserList){
        for (User user : delUserList){
            userService.delete(user.getId());
            userRoleService.deleteRoleByUserId(user.getId());
        }
        return Result.ok();
    }

    @GetMapping("/getLikeUsers")
    public Result<HashMap> getLikeUsers(@RequestParam("usernameLike") String userLike , @RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        Result<HashMap> res = new Result<>();
        List<User> userList = userService.getLikeUsers("%" + userLike + "%" , page ,pageSize);
        HashMap map = new HashMap();
        map.put("userList",userList);
        map.put("total",userService.countUserLikeTotal("%" + userLike + "%"));
        res.setResult(map);
        return res.success("获取用户列表成功！");
    }

    @DeleteMapping("/delOneUser")
    public Result delOneUser(@RequestParam Integer userId){
        userService.delete(userId);
        userRoleService.deleteRoleByUserId(userId);
        return Result.ok();
    }

    @GetMapping("/queryUserList")
    public Result<HashMap> getUserList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,
                                      @RequestParam( value = "query" ,required = false) String query , @RequestParam("deptId") Integer deptId){
        Result<HashMap> res = new Result<>();
        List<User> userList = userService.getUserList(page,pageSize,query,deptId);
        Integer total = userService.countUserTotal(query,deptId);
        HashMap map = new HashMap();
        map.put("userList",userList);
        map.put("total",total);
        res.setResult(map);
        return res.success("用户列表获取成功！");
    }

    @GetMapping("/getAllUser")
    public Result<List<User>> getAllUser(){
        Result<List<User>> res = new Result<>();
        List<User> users = userService.queryAll();
        res.setResult(users);
        return res.success("获取成功");
    }

    @GetMapping("/queryUserList2")
    public Result<HashMap> getUserList2(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,
                                       @RequestParam( value = "query" ,required = false) String query , @RequestParam("deptId") Integer deptId,
                                        @RequestParam("userId") Integer userId){
        Result<HashMap> res = new Result<>();
        List<User> userList = userService.getUserList2(page,pageSize,query,deptId,userId);
        Integer total = userService.countUserTotal2(query,deptId,userId);
        HashMap map = new HashMap();
        map.put("userList",userList);
        map.put("total",total);
        res.setResult(map);
        return res.success("用户列表获取成功！");
    }
}
