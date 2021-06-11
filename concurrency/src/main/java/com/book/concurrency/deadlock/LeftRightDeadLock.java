package com.book.concurrency.deadlock;

import javax.naming.InsufficientResourcesException;

/**
 * 通过锁顺序来避免死锁
 * @author zhangyoubao
 * @version 2021/5/31
 */
public class LeftRightDeadLock {

    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct, final Account toAcct, Double amount)
            throws InsufficientResourcesException {
        class Helper {
            public void transfer() throws InsufficientResourcesException {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientResourcesException();
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
