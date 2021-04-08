package com.book.spring.chapter2.impl;

import com.book.spring.chapter2.ifs.CompactDisc;
import com.book.spring.chapter2.ifs.MediaPlayer;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
@Component
public class CDPlayer implements MediaPlayer {

    private final CompactDisc cd;

    @Inject
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
