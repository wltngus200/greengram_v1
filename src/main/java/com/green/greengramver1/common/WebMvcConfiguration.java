package com.green.greengramver1.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final String uploadPath;

    public WebMvcConfiguration(@Value("${file.directory}")String uploadPath){
        this.uploadPath=uploadPath;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/pic/**")
                //주소값이 이렇게 들어오면 아래쪽 uploadPath로 매핑
                //pic/user/4/(파일이름)
                .addResourceLocations("file:"+uploadPath);
    }
}
