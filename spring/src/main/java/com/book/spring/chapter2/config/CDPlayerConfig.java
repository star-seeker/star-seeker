package com.book.spring.chapter2.config;

import com.book.spring.chapter2.ifs.CompactDisc;
import com.book.spring.chapter2.ifs.MediaPlayer;
import com.book.spring.chapter2.impl.CDPlayer;
import com.book.spring.chapter2.impl.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ComponentScan 默认扫描本类所在包
 * @author zhangyoubao
 * @version 2021/4/8
 */
@Configuration
//@ComponentScan(basePackageClasses = SgtPeppers.class)
public class CDPlayerConfig {

    @Bean
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

    @Bean
    public MediaPlayer player(CompactDisc cd) {
        return new CDPlayer(cd);
    }
}
