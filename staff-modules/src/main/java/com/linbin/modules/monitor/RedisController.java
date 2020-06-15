package com.linbin.modules.monitor;

import com.linbin.utils.RedisUtils;
import com.linbin.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LinBin
 * @Date: 2020/4/27 17:06
 * @Description:
 */
@Transactional
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @GetMapping("/getKeysCount")
    public Result getKeysCount(){
        RedisUtils redisUtils = new RedisUtils();
        Integer count = redisUtils.getKesCount();
        return Result.ok(count);
    }
}
