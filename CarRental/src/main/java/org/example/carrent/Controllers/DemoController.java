package org.example.carrent.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/public")
    public String hello() {
        return "Hello Public";
    }

    @GetMapping("/private")
    public String hello2() {
        return "Hello Private";
    }
}