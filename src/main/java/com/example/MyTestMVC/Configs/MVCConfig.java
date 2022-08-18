package com.example.MyTestMVC.Configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/images/loaded/**")
                .addResourceLocations("file://"+uploadPath+"/");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/authorization").setViewName("authorization");
    }
}
