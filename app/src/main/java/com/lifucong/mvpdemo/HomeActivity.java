package com.lifucong.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeView{

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.prg_loading)
    ProgressBar prgLoading;
    private ArrayAdapter<String> adapter;
    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        homePresenter=new HomePresenter();
        homePresenter.onCreate();
        homePresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.onDestroy();
        homePresenter.datachView();
    }

    @OnClick(R.id.btn_refresh)
    public void refreshData() {
        homePresenter.loadData();
    }


    @Override
    public void showLoading(){
        prgLoading.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.GONE);
    }
    @Override
    public void hideLoading(){
        prgLoading.setVisibility(View.GONE);
        btnRefresh.setVisibility(View.VISIBLE);
    }
    @Override
    public void refreshListView(List<String>list){
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
