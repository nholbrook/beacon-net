package com.nickholbrook.beaconnet.config;

import com.nickholbrook.beaconnet.service.DynamoDBAuthService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
public class MvcConfig implements WebMvcConfigurer {

	String workingDirectory = System.getProperty("user.dir");

	private String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/resources/", "classpath:/static/", "classpath:/public/", "file:///" + workingDirectory + "/node_modules/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/raw").setViewName("raw");
		registry.addViewController("/404").setViewName("404");
		registry.addViewController("/500").setViewName("500");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/login").setViewName("login");
	}

}