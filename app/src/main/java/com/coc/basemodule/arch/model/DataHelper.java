package com.coc.basemodule.arch.model;

/**
 * Created by tang on 2017/7/14.
 */

public class DataHelper {

    private static IApi mApiService;//用于远程数据，主要是从服务器获取数据

    private DataHelper() {
//        mApiService = ApiRetrofitImpl.newInstance();//当需要切换到其他网络实现时，在这里改动
        mApiService = ApiImpl.getInstance();//当需要切换到其他网络实现时，在这里改动

    }


    public static DataHelper getInstance() {
        return Holder.INSTANCE;
    }

    public IApi api() {
        return mApiService;
    }

    //静态内部类 单例
    private static class Holder {
        private static final DataHelper INSTANCE = new DataHelper();
    }
}
