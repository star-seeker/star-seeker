package com.book.spring.chapter1.config;

import com.book.spring.chapter1.ifs.Knight;
import com.book.spring.chapter1.ifs.Quest;
import com.book.spring.chapter1.impl.BraveKnight;
import com.book.spring.chapter1.impl.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
@Configuration
public class KnightConfig {

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }

    @Bean
    public Knight knight() {
        return new BraveKnight(quest());
    }
}
