package com.book.spring.chapter3.config;

import com.book.spring.chapter3.bean.MagicBean;
import com.book.spring.chapter3.bean.Notepad;
import com.book.spring.chapter3.ifs.Cold;
import com.book.spring.chapter3.ifs.Creamy;
import com.book.spring.chapter3.ifs.Fruity;
import com.book.spring.chapter3.impl.Cake;
import com.book.spring.chapter3.impl.Cookies;
import com.book.spring.chapter3.impl.IceCream;
import com.book.spring.chapter3.impl.MagicExistsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
@Configuration
public class BeanConfig {

    @Bean
    @Conditional(MagicExistsCondition.class)
    public MagicBean magicBean() {
        return new MagicBean();
    }

    @Bean
    public Cake cake() {
        return new Cake();
    }

    @Bean
    @Cold
    @Fruity
    public Cookies cookies() {
        return new Cookies();
    }

    @Bean
    @Cold
    @Creamy
    public IceCream iceCream() {
        return new IceCream();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS) // 指定作用域
    public Notepad notepad() {
        return new Notepad();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
