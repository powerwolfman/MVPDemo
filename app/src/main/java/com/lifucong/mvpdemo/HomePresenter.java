package com.lifucong.mvpdemo;

import android.support.annotation.UiThread;

import com.lifucong.mvpdemo.basemvp.MvpPresenter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class HomePresenter extends MvpPresenter<HomeView> implements HomeModel.Model{

    @UiThread
    public void loadData(){
        super.getView().showLoading();
        new HomeModel(this).asyncLoadData();
    }

    @UiThread
    @Override
    public void setData(List<String> datas) {
        super.getView().hideLoading();
        if (datas==null) {
            super.getView().showMessage("未知错误");
            return;
        }
        super.getView().refreshListView(datas);
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
