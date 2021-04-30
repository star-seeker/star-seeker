package com.goose.idworker;

public interface RandomCodeStrategy {
    void init();
    int prefix();
    int next();
    void release();
}
