package com.linbin.modules.Login;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.constant.CommonConstant;
import com.linbin.entity.VerifyImg;
import com.linbin.utils.VerifyCodeUtils;
import com.linbin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@Transactional
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/getCheckCode")
    public Result<VerifyImg> getCheckCode(){
        Result<VerifyImg> res = new Result<>();
        try {
            VerifyImg verifyImg = VerifyCodeUtils.VerifyCode(100,40,4);
            res.setResult(verifyImg);
            res.setCode(CommonConstant.SC_OK_200);
            res.setMessage("验证码获取成功!");
            return res;
        }catch (IOException e){
            return res.error500("验证码生成出错,请检查后台系统!");
        }
    }

    @PostMapping("/retrievePwd")
    public Result retrievePwd(@RequestParam String username , @RequestParam String phone){
        User user = userService.queryOneByUserName(username);
        if (user == null){
            return Result.error("用户不存在！请检查用户名是否输入正确!");
        }else if (user.getPhone() == null){
            return Result.error("该用户未绑定手机号！请先绑定！");
        }else if (!user.getPhone().equalsIgnoreCase(phone)){
            return Result.error("该账户绑定手机号不是此号码！请检查手机号是否输入正确！");
        }
        return Result.ok();
    }

    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map map){
        String username = (String) map.get("username");
        String password = new BCryptPasswordEncoder().encode((String) map.get("password"));
        User user = userService.queryOneByUserName(username);
        if (user == null){
            return Result.error("用户不存在！请检查用户名是否输入正确！");
        }else if (user.getPassword().equalsIgnoreCase(password)){
            return Result.error("新密码不能与原密码一致！");
        }
        userService.updatePwdByUserName(username,password);
        return Result.ok();
    }
}
