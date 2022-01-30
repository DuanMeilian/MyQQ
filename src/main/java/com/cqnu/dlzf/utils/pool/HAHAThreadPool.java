package com.cqnu.dlzf.utils.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HAHAThreadPool {

    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(100);

    public static void execute(Runnable task){
        SERVICE.execute(task);
    }
}
