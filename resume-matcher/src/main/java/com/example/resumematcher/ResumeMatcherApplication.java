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
        System.out.println("Check ranked candidates API: http://localhost:8080/api/ranked-candidates");
        System.out.println("History: http://localhost:8080/h2-console/login.do?jsessionid=2913159f941203833f3b16279263c7dd");
        System.out.println("-------------------------------------------------");
    }
}