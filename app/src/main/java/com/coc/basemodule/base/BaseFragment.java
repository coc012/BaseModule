package com.coc.basemodule.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coc.basemodule.utils.utils_base.MyLog;


/**
 * Created by tang on 2016/12/28.
 */

public class BaseFragment extends Fragment {

    //TODO:尽快完成相关类的变更，随后将会 设置为抽象方法
//    protected abstract void saveFieldStateForReCreate(Bundle outState);
    protected void saveFieldStateForReCreate(Bundle outState) {
    }


    //TODO:尽快完成相关类的变更，随后将会 设置为抽象方法
//    protected abstract void restoreFieldStateFromSaved(Bundle savedInstanceState);
    protected void restoreFieldStateFromSaved(Bundle savedInstanceState) {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            MyLog.e("save", "restoreFieldStateFromSaved");
            restoreFieldStateFromSaved(savedInstanceState);
        }
    }

    //务必进行onSaveInstanceState 和onActivityCreated 的保存变量
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            MyLog.e("save", "saveFieldStateForReCreate");
            saveFieldStateForReCreate(outState);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyLog.e("TagResume", "onResume" + this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLog.e("TagResume", "onResume" + this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MyLog.e("TagResume", "onPause" + this);
    }


    @Override
    public void onDestroy() {
        MyLog.e("TagResume", "onDestroy" + this);
        super.onDestroy();
    }
}
