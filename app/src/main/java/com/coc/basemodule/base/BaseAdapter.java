package com.coc.basemodule.base;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tang on 2016/11/23.
 * 通用adapter ，自提供mlist，传入泛型即可，提供常用集合操作
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    protected List<T> mList;
    protected Context mContext;

    public BaseAdapter(Context context) {
        this(context, null);
    }

    public BaseAdapter(Context context, List<T> list) {
        this.mContext = context;
        if (list == null) list = new ArrayList<>();
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }


    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 获取当前集合
     */
    public List<T> getData() {
        return mList;
    }


    /**
     * 向当前集合提价一个集合
     */
    public void setDataList(List<T> list) {
        if (list == null) return;
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }


    /**
     * 向当前集合添加一个数据
     */
    public void addData(T itemData) {

        this.mList.add(itemData);
        notifyDataSetChanged();
    }


    /**
     * 在当前集合指定位置 添加一个数据
     */
    public void addData(int position, T data) {
        if (0 <= position && position < mList.size()) {
            mList.add(position, data);
            notifyDataSetChanged();
        } else {
            throw new ArrayIndexOutOfBoundsException("inserted position most greater than 0 and less than data size");
        }
    }


    /**
     * 在当前集合指定位置 添加一组数据
     */
    public void addData(int position, List<T> data) {
        if (0 <= position && position < mList.size()) {
            mList.addAll(position, data);
            notifyDataSetChanged();
        } else {
            throw new ArrayIndexOutOfBoundsException("inserted position most greater than 0 and less than data size");
        }
    }


    /**
     * 向当前集合添加一组数据
     *
     * @param newData
     */
    public void addData(List<T> newData) {
        this.mList.addAll(newData);
        notifyDataSetChanged();
    }

    /**
     * 移除当前集合的指定元素
     *
     * @param position
     */
    public void remove(int position) {
        if (this.mList.size() > 0) {
            mList.remove(position);
            notifyDataSetChanged();
        }

    }


    /**
     * 清空当前数据集合
     */
    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }


}
