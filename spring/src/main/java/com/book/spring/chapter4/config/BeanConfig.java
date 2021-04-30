package com.book.spring.chapter4.config;

import com.book.spring.chapter4.aspect.Audience;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhangyoubao
 * @version 2021/4/9
 */
@Configuration
@ComponentScan(basePackages = "com.book.spring.chapter4")
@EnableAspectJAutoProxy
public class BeanConfig {

    @Bean
    public Audience audience() {
        return new Audience();
    }
}
