package com.lifucong.mvpdemo.basemvp;

import com.lifucong.mvpdemo.HomeView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/10/13.
 *
 */

public abstract class MvpPresenter <V extends MvpView>{

    private V mView;

    protected final V getView(){
        return mView;
    }
    public final void attachView(V view){
        mView = view;
    }

    public void onCreate(){
        EventBus.getDefault().register(this);
    }
    public void onDestroy(){
        EventBus.getDefault().unregister(this);
    }
    /**
     * 解绑视图
     * Activity中OnDestroy()方法中调用
     */
    public final void datachView(){
        //使用NullObject
        mView=null;
        mView=getNullObject();
    }
    public abstract V getNullObject();
}
