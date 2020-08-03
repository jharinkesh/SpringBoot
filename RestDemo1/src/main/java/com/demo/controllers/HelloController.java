package com.demo.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.ServiceResponse;

@RestController
@RequestMapping("/demo")
public class HelloController {

    @Autowired
    MessageSource messageSource;

    @GetMapping(path = "/hello")
    public String hello() {
	return "hello";
    }

    @GetMapping(path = "/hello-bean")
    public ServiceResponse helloBean() {
	return new ServiceResponse("hello");
    }

    @GetMapping(path = "/hello-bean-int")
    public String helloBeanInt(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
	return messageSource.getMessage("demo.message", null, locale);
    }

    @GetMapping(path = "/hello-bean-int-auto")
    public String helloBeanIntAuto() {
	return messageSource.getMessage("demo.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping(path = "/hello/{name}")
    public String hello1(@PathVariable String name) {
	return "hello: " + name;
    }

}
