package com.lifucong.mvpdemo;

import android.view.View;
import android.widget.Toast;

import com.lifucong.mvpdemo.basemvp.MvpView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public interface  HomeView extends MvpView{
    void showLoading();
    void hideLoading();
    void refreshListView(List<String> list);
    void showMessage(String msg);
}
