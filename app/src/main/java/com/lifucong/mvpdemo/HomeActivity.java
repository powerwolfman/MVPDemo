package com.lifucong.mvpdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.prg_loading)
    ProgressBar prgLoading;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_refresh)
    public void refreshData() {
        new LoadTask().execute();
    }

    private class LoadTask extends AsyncTask<Void,Void,List<String>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                showMessage(e.getMessage());
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
            hideLoading();
            refreshListView(strings);
        }
    }

    private void showLoading(){
        prgLoading.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.GONE);
    }
    private void hideLoading(){
        prgLoading.setVisibility(View.GONE);
        btnRefresh.setVisibility(View.VISIBLE);
    }
    private void refreshListView(List<String>list){
        adapter.clear();
       adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }
    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
