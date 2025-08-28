package com.example.restservice;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {

    @GetMapping("/random")
    public int random() {
        // returns a pseudorandom int between 1 (inclusive) and 11 (exclusive)
        return ThreadLocalRandom.current().nextInt(1, 11);
    }
}
