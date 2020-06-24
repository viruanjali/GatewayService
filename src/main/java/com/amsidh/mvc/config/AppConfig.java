package com.amsidh.mvc.config;

import com.amsidh.mvc.filter.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean registerRequestLoggingFilter(){
        FilterRegistrationBean reg = new FilterRegistrationBean(getRequestLoggingFilter());
        reg.setOrder(1);
        return reg;
    }

    @Bean
    public RequestLoggingFilter getRequestLoggingFilter(){
        return new RequestLoggingFilter();
    }
}
