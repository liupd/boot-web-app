package com.wa.net;


import com.wa.net.aop.ServiceLoggingAop;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigAop {

    @Bean
    @ConfigurationProperties(prefix = "spring.aop.service-logging")
    public ServiceLoggingAop serviceLoggingAop() {
        return new ServiceLoggingAop();
    }

}
