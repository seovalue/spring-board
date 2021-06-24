package com.joanne.board.web;

import com.joanne.board.web.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello/dto")
    public HelloResponse hello(@RequestParam("name") String name,
                               @RequestParam("amount") int amount) {
        return new HelloResponse(name, amount);
    }
}
