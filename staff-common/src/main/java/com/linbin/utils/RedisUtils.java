package com.linbin.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Set;


/**
 * @Author: LinBin
 * @Date: 2020/4/20 10:11
 * @Description:
 */
@Slf4j
public class RedisUtils {

    private Jedis jedis;

    public RedisUtils() {
        this.jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        jedis.select(5);
    }

    /**
     * 判断redis是否存在key
     * @param key
     * @return
     */
   public boolean isKeyExits(String key){
        return jedis.exists(key);
   }

    /**
     * 从redis获取字符类型的key
     * @param key
     * @return
     */
   public String getStringKey(String key){
       if (!isKeyExits(key)){
           log.info("此key不存在！");
           return null;
       }
       String value = jedis.get(key);
       log.info("从redis缓存获取: key = " + key + ", value = " + value);
       return value;
   }

    /**
     * 插入redis字符类型的key
     * @param key
     * @param value
     */
    public void setStringKey(String key , String value){
        jedis.set(key,value);
        log.info("插入redis缓存: key = " + key + ", value = " + value);
    }

    /**
     * 设置过期时间
     * @param key
     * @param seconds
     */
    public void setExpiration(String key,Integer seconds){
        jedis.expire(key,seconds);
        log.info("key有效时间为: expiration = " + seconds/3600 + "小时");
    }

    /**
     * 获取所有key的个数
     * @return
     */
    public Integer getKesCount(){
        Set set = jedis.keys("*");
        return set.size();
    }
}
