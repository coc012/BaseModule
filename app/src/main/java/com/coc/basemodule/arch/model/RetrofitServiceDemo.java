package com.coc.basemodule.arch.model;

import android.support.annotation.NonNull;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tang on 2017/7/14.
 */

public interface RetrofitServiceDemo {

    //启动打点
    @NonNull
    @GET("https://api.youxinche.com/ab.html")
    Observable<Object> api_tag_lanunch(@QueryMap Map<String, Object> params);
}
