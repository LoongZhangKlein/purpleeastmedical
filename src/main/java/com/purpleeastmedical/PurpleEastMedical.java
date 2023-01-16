package com.purpleeastmedical;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author loongzhang
 * @Description DOING
 * @date ${DATE}-${TIME}
 */

@SpringBootApplication
// 扫描mapper文件
@MapperScan("com.purpleeastmedical.mapper")
public class PurpleEastMedical {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PurpleEastMedical.class, args);
        Environment environment = run.getBean(Environment.class);
        String port = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        System.out.println("访问地址如下");
        System.out.println("http://localhost:"+port+contextPath);
    }
}