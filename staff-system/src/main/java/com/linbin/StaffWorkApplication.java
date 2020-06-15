package com.linbin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName com.linbin.StaffWorkApplication
 * @Author linBin
 * @Date 2020/3/23 10:25
 * @Description ：staff-work启动类
 */

@Slf4j
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class StaffWorkApplication {
    public static void main(String[] args) throws UnknownHostException{
        ConfigurableApplicationContext application = SpringApplication.run(StaffWorkApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Staff-Work is running! Access URLs:\n\t" +
                "Local: http://localhost:" + port + path + "/\n\t" +
                "----------------------------------------------------------");
    }
}
