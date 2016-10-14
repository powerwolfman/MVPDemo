package com.lifucong.mvpdemo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class HomeEvent {
    public final List<String> datas;

    public HomeEvent(List<String> datas) {
        this.datas = datas;
    }

    public HomeEvent() {
        datas = null;
    }
}
