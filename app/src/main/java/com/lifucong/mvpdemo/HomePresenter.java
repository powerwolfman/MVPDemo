package com.lifucong.mvpdemo;

import android.support.annotation.UiThread;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class HomePresenter implements HomeModel.Model{
    private HomeView mHomeView;

    public HomePresenter(HomeView homeView) {
        this.mHomeView = homeView;
    }

    @UiThread
    public void loadData(){
        mHomeView.showLoading();
        new HomeModel(this).asyncLoadData();
    }

    @UiThread
    @Override
    public void setData(List<String> datas) {
        mHomeView.hideLoading();
        if (datas==null) {
            mHomeView.showMessage("未知错误");
            return;
        }
        mHomeView.refreshListView(datas);
    }
}
