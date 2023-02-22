package com.example.cameltcp;


import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {

    @Autowired
    private ProducerTemplate producerTemplate;


    @GetMapping("test")
    public String test() {
        producerTemplate.sendBodyAndHeaders("direct:test", "hi", new HashMap<>());
        return "Success";
    }
}
