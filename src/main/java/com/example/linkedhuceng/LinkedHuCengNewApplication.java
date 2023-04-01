package com.example.linkedhuceng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// @ComponentScan it seems it is unnecessary but it was in the video
@SpringBootApplication
@EntityScan
public class LinkedHuCengNewApplication extends SpringBootServletInitializer {

    // I have no idea what does that do and why we extended that class
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(LinkedHuCengNewApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LinkedHuCengNewApplication.class, args);
    }

}
