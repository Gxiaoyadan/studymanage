package com.example.studymanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class StudymanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudymanageApplication.class, args);
    }

}
