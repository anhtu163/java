package com.example.securingweb;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer, ErrorController {
	private final static String PATH = "/error";
	@Override
	@RequestMapping(PATH)
	@ResponseBody
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "No Mapping Found";
	}
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/user").setViewName("user");
		registry.addViewController("/admin").setViewName("admin");

	}

}
