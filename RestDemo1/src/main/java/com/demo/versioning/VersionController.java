package com.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/v1/person")
    public Person1 getPerson1() {
	return new Person1("baba");
    }

    @GetMapping("/v2/person")
    public Person2 getPerson2() {
	return new Person2(new Name("baba", "ultimate"));
    }

    @GetMapping(value = "/person", params = "version=1")
    public Person1 getPerson1N() {
	return new Person1("baba");
    }

    @GetMapping(value = "/person", params = "version=2")
    public Person2 getPerson2N() {
	return new Person2(new Name("baba", "ultimate"));
    }

    @GetMapping(value = "/person/header", headers = "version=1")
    public Person1 getPerson1NH() {
	return new Person1("baba");
    }

    @GetMapping(value = "/person/header", headers = "version=2")
    public Person2 getPerson2NH() {
	return new Person2(new Name("baba", "ultimate"));
    }

}
