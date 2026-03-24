package com.example.pwps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /picture/** 映射到本项目 src/main/picture/ 目录
        String projectDir = System.getProperty("user.dir");
        String pictureDir = Paths.get(projectDir, "src", "main", "picture").toUri().toString();
        registry.addResourceHandler("/picture/**")
                .addResourceLocations(pictureDir);
    }
}

