package com.lifucong.mvpdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class HomeModel {
    private Thread mThread;
    private Model mModel;
    private Handler handler;

    public HomeModel(Model model){
        mModel=model;
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==100) {
                    List<String > datas= (List<String>) msg.obj;
                    mModel.setData(datas);
                }
            }
        };
    }

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
                List<String> datas=new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    datas.add("我是第"+i+"条数据，嘿嘿！");
                }
                Message msg=Message.obtain();
                msg.obj=datas;
                msg.what=100;
                handler.sendMessage(msg);
            }
        });
        mThread.start();
    }

    public interface Model{
        void setData(List<String>datas);
    }
}
