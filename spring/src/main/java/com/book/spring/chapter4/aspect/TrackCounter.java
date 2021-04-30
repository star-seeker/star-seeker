package com.book.spring.chapter4.aspect;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author zhangyoubao
 * @version 2021/4/9
 */
@Aspect
public class TrackCounter {

    private final Map<Integer, Integer> trackCounts = new HashMap<>();

    @Pointcut(
            "execution(* com.book.spring.chapter4.ifs.CompactDisc.playTrack(int)) && args(trackNumber))"
    )
    public void trackPlayed(int trackNumber) {};

    @Before(value = "trackPlayed(trackNumber)", argNames = "trackNumber")
    public void countTrack(int trackNumber) {
        int currentCount = getPlayCount(trackNumber);
        trackCounts.put(trackNumber, currentCount + 1);
    }

    public int getPlayCount(int trackNumber) {
        return trackCounts.getOrDefault(trackNumber, 0);
    }
}
