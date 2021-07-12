package com.yergbro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.yergbro.dao")
public class BroMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BroMusicApplication.class, args);
        System.out.println("----------------------后台启动成功---------------------");
    }
}
