package com.book.spring.chapter1;

import com.book.spring.chapter1.ifs.Knight;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
public class KnightMain {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlContent = new ClassPathXmlApplicationContext("knight.xml");
        Knight bean = xmlContent.getBean(Knight.class);
        bean.embarkOnQuest();
        xmlContent.close();
    }
}
