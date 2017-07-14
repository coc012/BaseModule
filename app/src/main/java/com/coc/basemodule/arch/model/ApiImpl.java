package com.coc.basemodule.arch.model;

import android.arch.lifecycle.LiveData;

import com.coc.basemodule.arch.bean.StatusBean;

import java.util.Map;

/**
 * Created by tang on 2017/7/14.
 */

public class ApiImpl implements IApi {


    private RetrofitServiceDemo service;
    private RxSender retrofitSender;


    //构造方法私有
    private ApiImpl() {
        retrofitSender = RxSender.getInstance();
        service = RxSender.getInstance().getService();
    }

    //获取单例
    public static ApiImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void pull_getImg(LiveData<StatusBean> status, Map<String, Object> params, JsonCallback<Object> callback) {
    }

    private static class SingletonHolder {
        private static final ApiImpl INSTANCE = new ApiImpl();
    }


}
