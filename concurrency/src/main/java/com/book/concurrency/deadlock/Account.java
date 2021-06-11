package com.book.concurrency.deadlock;

/**
 * @author zhangyoubao
 * @version 2021/6/8
 */
public class Account {

    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void debit(Double amount) {

    }

    public void credit(Double amount) {

    }
}
