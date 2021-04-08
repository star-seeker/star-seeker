package com.book.spring.chapter1.impl;

import java.io.PrintStream;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
public class Minstrel {

    private final PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest() {
        stream.println("Fa la la, the knight is so brave!");
    }

    public void singAfterQuest() {
        stream.println("Tee hee hee, the brave knight did embark on a quest!");
    }
}
