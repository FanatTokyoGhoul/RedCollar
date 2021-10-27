package com.example.redcollar1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = {"com.example", "common.lib"})
public class RedCollar1Application {

    public static void main(String[] args) {
        SpringApplication.run(RedCollar1Application.class, args);
    }

}
