package me.will.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("me.will.crawler")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
