package com.yergbro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yergbro.dao")
public class BroMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BroMusicApplication.class, args);
        System.out.println("----------------------后台启动成功---------------------");
    }

}
