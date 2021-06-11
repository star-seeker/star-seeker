package com.book.concurrency.deadlock;

/**
 * 在基于散列的Map中使用锁分段技术
 * @author zhangyoubao
 * @version 2021/6/10
 */
public class StripedMap {
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    private static class Node {
        Object key;
        Object value;
    }

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (int i = 0; i < buckets.length; i++) {
                Node m = buckets[hash];
                if (m.key.equals(key)) {
                    return m.value;
                }
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }
}
