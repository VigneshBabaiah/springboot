package com.css.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
 
@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {
 
    private SpringSwaggerConfig springSwaggerConfig;
 
    @Autowired 
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }
 
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        String matcher = "^((?!repository|alps|env|error).)*$"; //;
    	return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(new ApiInfo(
                        "Pricing API",
                        "API for Pricing",
                        "",
                        "",
                        "",
                        ""
                ))
                //Here we specify URI patterns which will be included in Swagger docs. Use regex for this purpose. ("/*.*)
                .includePatterns(matcher);
    }
 
}