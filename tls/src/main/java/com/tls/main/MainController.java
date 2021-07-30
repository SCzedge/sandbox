package com.tls.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    @GetMapping("/")
    public String main() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        return uuid;
    }
}
