package com.linbin.utils;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * @Author: LinBin
 * @Date: 2020/3/28 14:46
 * @Description:
 */
public class MD5Utils {
    /**
     * @param text 明文
     * @param key 密钥
     * @return 密文
     */
    // 带秘钥加密
    public static String md5WithKey(String text, String key) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text + key);
        return md5str;
    }

    // 不带秘钥加密
    public static String md5WithoutKey(String text) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text);
        return md5str;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     */
    // 根据传入的密钥进行验证
    public static boolean verifyWithKey(String text, String key, String md5) throws Exception {
        String md5str = md5WithKey(text, key);
        if (md5str.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }

    public static boolean verifyWithoutKey(String text, String md5) throws Exception {
        String md5str = md5WithoutKey(text);
        if (md5str.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }
}
