package com.book.spring.chapter2.impl;

import com.book.spring.chapter2.ifs.CompactDisc;
import org.springframework.stereotype.Component;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
@Component
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Club Brand";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
