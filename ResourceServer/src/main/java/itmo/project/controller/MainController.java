package itmo.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/main")
    //the aim of the project to secure end point with jwt.
    //The logic of the controller doesn't matter.
    public String hello() {
        return "Hello";
    }
}
