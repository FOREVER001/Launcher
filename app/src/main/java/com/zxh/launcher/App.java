package com.zxh.launcher;

import android.app.Application;
import android.os.Debug;
import android.os.Environment;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App extends Application {
    ExecutorService mExecutorService;
    int coreSize;
    @Override
    public void onCreate() {
        super.onCreate();
        coreSize=Runtime.getRuntime().availableProcessors();
        mExecutorService= Executors.newFixedThreadPool(Math.max(2,Math.min(coreSize-1,4)));
//        File file=new File(Environment.getExternalStorageDirectory(),"app.trace");
//        Debug.startMethodTracing(file.getAbsolutePath());

        async(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
        async(new Runnable() {
            @Override
            public void run() {
                test();
            }
        });

//        Debug.stopMethodTracing();
    }
    private void async(Runnable runnable){
        mExecutorService.submit(runnable);
    }
    private void init() {
      a();
    }

    private void a() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b();
        test();
    }

    private void test() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void b() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
