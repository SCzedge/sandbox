package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        String tempDir = "C:\\Users\\Administrator\\Desktop\\test\\";
        Test test =new Test();
        test.fileTest(tempDir);

        Zip4jTest zip4jTest = new Zip4jTest();
        zip4jTest.getZippedLogs(tempDir);
    }

}
