package com.coc.basemodule.base;

/**
 * Created by tang on 2016/12/22.
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.coc.basemodule.utils.utils_base.MyLog;


/**
 * 提供业务相关的基类实现 大部分功能都转移到 MyApplication ActivityLifecycleCallbacks 实现
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            MyLog.e("save", "activity restoreFieldStateFromSaved:" + this);
            restoreFieldStateFromSaved(savedInstanceState);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            MyLog.e("save", "activity saveFieldStateForReCreate:" + this);
            saveFieldStateForReCreate(outState);
        }
    }

    //TODO:尽快完成相关类的变更，随后将会 设置为抽象方法
//    protected abstract void saveFieldStateForReCreate(Bundle outState);
    protected void saveFieldStateForReCreate(Bundle outState) {
    }


    //TODO:尽快完成相关类的变更，随后将会 设置为抽象方法
//    protected abstract void restoreFieldStateFromSaved(Bundle savedInstanceState);
    protected void restoreFieldStateFromSaved(Bundle savedInstanceState) {
    }
}
