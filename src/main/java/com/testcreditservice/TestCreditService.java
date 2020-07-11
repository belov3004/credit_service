package com.testcreditservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class TestCreditService extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplication(TestCreditService.class).run(args);
    }

}
