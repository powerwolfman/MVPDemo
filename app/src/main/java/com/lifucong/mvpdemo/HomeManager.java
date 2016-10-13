package com.lifucong.mvpdemo;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class HomeManager {
    private HomeView mHomeView;

    public HomeManager(HomeView homeView) {
        this.mHomeView = homeView;
    }

    public void loadData(){
        new LoadTask().execute();
    }

    private class LoadTask extends AsyncTask<Void,Void,List<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mHomeView.showLoading();
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                mHomeView.showMessage(e.getMessage());
            }
            List<String>datas=new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                datas.add("我是第"+i+"条数据，嘿嘿！");
            }
            return datas;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            mHomeView.hideLoading();
            mHomeView.refreshListView(strings);
        }
    }
}
