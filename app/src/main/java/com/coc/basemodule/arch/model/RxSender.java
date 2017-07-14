package com.coc.basemodule.arch.model;


import android.support.annotation.NonNull;

import com.coc.basemodule.BuildConfig;
import com.coc.basemodule.utils.utils_base.MyLog;
import com.coc.basemodule.utils.utils_base.Prompt;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * http交互实现类
 * Created by tangahui on 2016/10/5.
 */

public class RxSender {

    private static final int DEFAULT_TIMEOUT = 8;///链接超时
    private static final int IO_TIMEOUT = 15;//读写超时
    private static String base_url = BuildConfig.DEBUG ? NetConfig.BASEURL_DEBUG : NetConfig.BASEURL_RELEASE;//测试服:正式服
    private RetrofitServiceDemo apiService;

    //构造方法私有
    private RxSender() {
        initService();
    }

    //获取单例
    public static RxSender getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 配置客户端
     *
     * @return
     */
    private OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //.addInterceptor(new ApikeyInterceptor()) //添加-apikey生成拦截器
                ;

        //仅debug 包里面，添加Stetho-okhttp的日志支持,正式版不输出
        if (BuildConfig.DEBUG) builder.addNetworkInterceptor(new StethoInterceptor());
        builder
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS);

        return builder.build();
    }

    /**
     * 初始化Retrofit 并创建代理类
     *
     * @return RetrofitServiceDemo
     */
    private RetrofitServiceDemo initService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(initClient())
                .addConverterFactory(FastJsonConverterFactory.create())//务必使用FastJson，data可能返回“”,Gson 无法解析，直接走onFailure
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(base_url)
                .build();
        apiService = retrofit.create(RetrofitServiceDemo.class);
        return apiService;
    }


    public RetrofitServiceDemo getService() {
        return apiService;
    }


    public void excuteJustPush(@NonNull Observable observable) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        MyLog.e("excuteJustPush", throwable.getMessage());
                    }
                });
    }

    //会加入loading view
    public <T> void excuteAync(final Object tag, @NonNull Observable<JsonBean<T>> observable, @NonNull final JsonCallback<T> callBack) {

        //联网前先检查网络
        //if (!checkNetStatus(callBack)) return;//如无网络 直接 totast提示 并调用 onError onEnd

        observable
                .retry(1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)//存在丢失onNext情况 http://www.open-open.com/lib/view/1479449748383
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        MyLog.e("TagResume", "excuteAync doOnSubscribe:" + tag);
                        callBack.onStart();

                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        MyLog.e("TagResume", "excuteAync doOnDispose:" + tag);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        MyLog.e("TagResume", "excuteAync doOnComplete:" + tag);

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        MyLog.e("TagResume", "excuteAync doFinally:" + tag);
                        callBack.onEnd();
                    }
                })
                .subscribe(new Observer<JsonBean<T>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        //MyLog.e("TagResume", "excuteAync onSubscribe:");
                    }

                    @Override
                    public void onNext(JsonBean<T> tJsonBean) {
                        MyLog.e("TagResume", "excuteAync onNext:" + tag);
                        handleCallSuccess(callBack, tJsonBean);//根据jsoncode，处理业务状态码
                    }

                    @Override
                    public void onError(Throwable t) {
                        MyLog.e("TagResume", "excuteAync onError:" + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        MyLog.e("TagResume", "excuteAync onComplete:" + tag);
                        //callBack.onEnd();
                    }


                });

    }


    /**
     * 更换app后需要 根据业务定制以下工作
     * 接收后处理
     * 主要推一些状况进行统一处理
     * 1.物理事件：无网络(此部分已被netBackFail完成，此部分请完成业务异常)
     * 2.逻辑异常：无权限、被踢下线
     */
    private <T> void handleCallSuccess(@NonNull JsonCallback<T> callBack, @NonNull JsonBean<T> jsonBean) {
        if (jsonBean == null) {
            return;
        }

        switch (jsonBean.getCode()) {
            case NetStausCode.CODE_NEED_LOGIN://-401,
                callBack.onError(null, jsonBean.getRealMessage());//一般为null都为业务逻辑判定为：异常
                break;

            case NetStausCode.CODE_NEED_VERIFY://-400008,
                callBack.onError(null, jsonBean.getRealMessage());//一般为null都为业务逻辑判定为：异常
                break;

            case NetStausCode.CODE_SUCCESS_EMPTY://200001,未能获取到数据
                callBack.onEmpty(jsonBean.getRealMessage());
                break;

            case NetStausCode.CODE_UPLOAD_PIC_SUCCESS://上传图片正常
            case NetStausCode.CODE_SUCCESS://业务逻辑：正常 调用onSuccess
                callBack.onSuccess(jsonBean, null);
                break;

            case NetStausCode.CODE_FORCE_UPDATE:
                Prompt.showToast(jsonBean.getRealMessage());
                break;

            default:
                callBack.onError(null, jsonBean.getRealMessage());//一般为null都为业务逻辑判定为：异常
                MyLog.e("RxSender", "The Method--handleCallSuccess state defalut :  should not call\n" + jsonBean.toString());
                break;
        }
    }


    //静态内部类 单例
    private static class Holder {
        private static final RxSender INSTANCE = new RxSender();
    }

}
