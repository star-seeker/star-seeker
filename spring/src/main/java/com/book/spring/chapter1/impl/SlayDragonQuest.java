package com.book.spring.chapter1.impl;

import com.book.spring.chapter1.ifs.Quest;
import java.io.PrintStream;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
public class SlayDragonQuest implements Quest {

    private final PrintStream stream;

    public SlayDragonQuest (PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}
