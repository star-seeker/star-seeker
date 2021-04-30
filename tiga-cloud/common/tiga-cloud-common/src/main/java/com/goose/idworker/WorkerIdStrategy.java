package com.goose.idworker;

public interface WorkerIdStrategy {
    void initialize();
    long availableWorkerId();
    void release();
}
