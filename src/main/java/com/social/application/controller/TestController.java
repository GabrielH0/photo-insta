package com.social.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/photo/test")
public class TestController {

    @GetMapping
    public String getTest(@RequestHeader Map<String, String> headers) {
        String user = headers.getOrDefault("authorization", "");
        return "test photos service";
    }
}