package com.coc.basemodule.arch.mvv;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.coc.basemodule.arch.bean.BizBean;
import com.coc.basemodule.arch.bean.StatusBean;

/**
 * Created by tang on 2017/7/14.
 */

public class DemoViewModel extends ViewModel {
    private String mTag;

    private MutableLiveData<StatusBean> viewStatus_LiveData;
    private MutableLiveData<BizBean> bizBean_LiveData;

    //初始化 观察者属性
    void init(String tag) {
        mTag = tag;
    }

    LiveData<StatusBean> getViewStatus_LiveData() {
        if (viewStatus_LiveData == null) {
            viewStatus_LiveData = new MutableLiveData<>();
            viewStatus_LiveData.setValue(new StatusBean());
        }
        return viewStatus_LiveData;
    }

    LiveData<BizBean> getBizBean_LiveData() {
        if (bizBean_LiveData == null) {
            bizBean_LiveData = new MutableLiveData<>();
            bizBean_LiveData.setValue(null);
            pullData();
        }
        return bizBean_LiveData;
    }


    //网络加载
    void pullData() {

    }
}
