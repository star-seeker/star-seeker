package com.book.spring.chapter5.ifs;

import com.book.spring.chapter5.model.Spittle;
import java.util.List;

/**
 * @author zhangyoubao
 * @version 2021/4/11
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);
}
