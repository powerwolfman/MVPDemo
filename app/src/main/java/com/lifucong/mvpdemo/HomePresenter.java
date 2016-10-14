package com.lifucong.mvpdemo;

import android.support.annotation.UiThread;

import com.lifucong.mvpdemo.basemvp.MvpPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class HomePresenter extends MvpPresenter<HomeView>{

    @UiThread
    public void loadData(){
        getView().showLoading();
        new HomeModel().asyncLoadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HomeEvent homeEvent){
        getView().hideLoading();
        if (homeEvent.datas==null) {
            getView().showMessage("未知错误");
            return;
        }
        getView().refreshListView(homeEvent.datas);
    }

    //一个HomeView接口（视图借口）空的实现
    public final HomeView getNullObject(){
        HomeView homeView=new HomeView() {
            @Override
            public void showLoading() {}
            @Override
            public void hideLoading() {}
            @Override
            public void refreshListView(List<String> list) {}
            @Override
            public void showMessage(String msg) {}
        };
        return homeView;
    }
}
