package com.ssafy.mvc.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	public OpenAPI api() {
		return new OpenAPI().info(new Info().title(""));
	}
}
