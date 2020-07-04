package com.zxh.launcher;

import android.os.Build;
import android.util.Log;
import android.view.Choreographer;

import androidx.annotation.RequiresApi;

public class FpsUtils {
    private long startTime;
    private int count = 0;
    private static final long INTERVAL = 160 * 1000 * 1000;//160ms
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void getFps(){
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                if(startTime==0){
                    startTime=frameTimeNanos;
                }
                long interval=frameTimeNanos-startTime;
                if(interval>INTERVAL){
                    double fps=(((double)(count*1000*1000))/interval)*1000L;
                    Log.d("FPS","fps= "+fps);
                    startTime=0;
                    count=0;
                }else {
                    count++;
                }
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }
}

