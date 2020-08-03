package com.demo.main;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
@ComponentScan(basePackages = { "com.demo" })
@EnableJpaRepositories(basePackages = { "com.demo" })
@EntityScan(basePackages = { "com.demo" })
public class RestDemo1Application {

    public static void main(String[] args) {
	SpringApplication.run(RestDemo1Application.class, args);
    }

    @Bean
    LocaleResolver localeResolver() {
	AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
	localeResolver.setDefaultLocale(Locale.US);
	return localeResolver;
    }

//    @Bean
//    ResourceBundleMessageSource bundleMessageSource() {
//	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	messageSource.setBasename("messages");
//	return messageSource;
//    }
}
