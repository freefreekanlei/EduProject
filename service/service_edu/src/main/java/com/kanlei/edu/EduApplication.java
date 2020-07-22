package com.kanlei.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * EnableSwagger2放在主配置上
 * @author kanlei
 * @creat 2020-07-20_16:57
 */
@EnableSwagger2
@SpringBootApplication
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }

}
