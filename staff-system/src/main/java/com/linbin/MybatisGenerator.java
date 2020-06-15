package com.linbin;

import org.mybatis.generator.api.ShellRunner;

public class MybatisGenerator {
    public static void main(String[] args) throws Exception{
        args = new String[] {"-configfile", "E:\\毕业设计\\project\\staff-work\\staff-system\\src\\main\\resources\\db\\mybatis-generator.xml", "-overwrite"};
        ShellRunner.main(args);
    }
}
