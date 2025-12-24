package com.example.resumematcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResumeMatcherApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ResumeMatcherApplication.class);
        app.run(args);

        System.out.println("-------------------------------------------------");
        System.out.println("🚀 Resume Matcher App is running!");
        System.out.println("Open in browser: http://localhost:8080/index.html");
        System.out.println("-------------------------------------------------");
    }
}
