package com.lifucong.mvpdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class HomeModel {
    private Thread mThread;

    public void asyncLoadData(){
        if (mThread!=null) {
            mThread.interrupt();
            mThread=null;
        }
        mThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (System.currentTimeMillis() % 2 == 0) {
                    EventBus.getDefault().post(new HomeEvent());
                } else {
                    List<String> datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        datas.add("我是第" + i + "条数据，嘿嘿！");
                    }
                    EventBus.getDefault().post(new HomeEvent(datas));
                }
            }
        });
        mThread.start();
    }

}
