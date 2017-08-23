package com.nowui.chuangshi.common.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private ExecutorService executor;
    public static final ThreadPool me = new ThreadPool();

    public ThreadPool() {
        executor = Executors.newFixedThreadPool(3);
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

}
