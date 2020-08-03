package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigController {

    @Autowired
    Configuration config;

    @GetMapping("/limits")
    public LimitConfig getConfig() {
	return new LimitConfig(config.getMinimum(), config.getMaximum());
    }
}
