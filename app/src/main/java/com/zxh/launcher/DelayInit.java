package com.zxh.launcher;

import android.os.Looper;
import android.os.MessageQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 延迟初始化
 */
public class DelayInit {
    private Queue<Runnable> delayQueue=new LinkedList<>();
    public void add (Runnable runnable){
        delayQueue.add(runnable);
    }
    public void start(){
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Runnable poll = delayQueue.poll();
                if(poll!=null){
                    poll.run();
                }
                return !delayQueue.isEmpty();
            }
        });
    }
}
