package com.JetecCRM.JetecCRM.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {	
    @Bean
    public FilterRegistrationBean<UserFilter> filterRegistrationBean(){
        FilterRegistrationBean<UserFilter> registrationBean = new FilterRegistrationBean<UserFilter>(new UserFilter());
        //过滤路径
        registrationBean.addUrlPatterns("/CRM/*","/system/*");
        //添加不过滤路径
        registrationBean.addInitParameter("noFilter","/,/two");
        registrationBean.setName("UserFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
   
    
}
