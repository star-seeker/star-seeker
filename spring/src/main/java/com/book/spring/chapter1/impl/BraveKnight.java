package com.book.spring.chapter1.impl;

import com.book.spring.chapter1.ifs.Knight;
import com.book.spring.chapter1.ifs.Quest;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
public class BraveKnight implements Knight {

    private final Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}
