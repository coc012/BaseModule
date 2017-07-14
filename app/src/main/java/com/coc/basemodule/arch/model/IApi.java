package com.coc.basemodule.arch.model;

import android.arch.lifecycle.LiveData;

import com.coc.basemodule.arch.bean.StatusBean;

import java.util.Map;

/**
 * Created by tang on 2017/7/14.
 */

public interface IApi {
    void pull_getImg(LiveData<StatusBean> status, Map<String, Object> params, JsonCallback<Object> callback);

}
