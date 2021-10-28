package com.JetecCRM.JetecCRM.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JianInterceptor implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/file/**").addResourceLocations("file:C:\\Users\\Rong\\Desktop\\apache-tomcat-9.0.53\\webapps\\CRM\\WEB-INF\\classes\\static\\file\\");
        registry.addResourceHandler("/images/userPic/**").addResourceLocations("file:C:\\OceanCatHouse\\OceanCatHouse\\src\\main\\resources\\static\\images\\userPic\\");
        registry.addResourceHandler("/images/mainpic/**").addResourceLocations("file:C:\\OceanCatHouse\\OceanCatHouse\\src\\main\\resources\\static\\images\\mainpic\\");
        registry.addResourceHandler("/images/stepPic/**").addResourceLocations("file:C:\\OceanCatHouse\\OceanCatHouse\\src\\main\\resources\\static\\images\\stepPic\\");
        registry.addResourceHandler("/images/shop/**").addResourceLocations("file:C:\\OceanCatHouse\\OceanCatHouse\\src\\main\\resources\\static\\images\\shop\\");
    }
}

