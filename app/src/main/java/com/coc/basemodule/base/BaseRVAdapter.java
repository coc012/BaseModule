package com.coc.basemodule.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tang on 2016/11/23.
 */

/**
 * 适用于Recycview的adaper
 */
public abstract class BaseRVAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mList;
    protected Context mContext;
    protected OnItemClickListener mOnItemClickListener;//item单击事件响应
    protected OnItemLongClickListener mOnItemLongClickListener;//item长按事件响应


    public BaseRVAdapter(Context context, List<T> list) {
        this.mContext = context;
        if (list == null) mList = new ArrayList<>();
        this.mList = list;
    }


    public BaseRVAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * 在当前集合末尾添加一组元素
     *
     * @return
     */
    public List<T> getData() {
        return mList;
    }


    public void setDataList(List<T> list) {
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }


    /**
     * 在当前集合末尾添加一个元素
     *
     * @param itemData
     */
    public void addData(T itemData) {
        if (this.mList.add(itemData)) {
            notifyItemInserted(this.mList.size());
        }
    }


    /**
     * 在当前集合指定位置添加 一个元素
     *
     * @param position
     */
    public void addData(int position, T data) {
        if (0 <= position && position < mList.size()) {
            mList.add(position, data);
            notifyItemInserted(position);
        } else {
            throw new ArrayIndexOutOfBoundsException("inserted position most greater than 0 and less than data size");
        }
    }


    /**
     * 在当前集合指定位置添加 一组元素
     */
    public void addData(int position, List<T> data) {
        if (0 <= position && position < mList.size()) {
            mList.addAll(position, data);
            notifyItemRangeInserted(position, data.size());
        } else {
            throw new ArrayIndexOutOfBoundsException("inserted position most greater than 0 and less than data size");
        }
    }


    /**
     * 在当前集合末尾添加 一组元素
     *
     * @param newData
     */
    public void addData(List<T> newData) {
        this.mList.addAll(newData);
        notifyItemRangeInserted(mList.size() - newData.size() + 1, newData.size());
    }


    /**
     * 移除当前集合指定位置的一个元素
     *
     * @param position
     */
    public void remove(int position) {
        if (this.mList.size() > 0) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 清空当前集合
     */
    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }


    public BaseRVAdapter setmItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
        return this;
    }


    public BaseRVAdapter setmItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.mOnItemLongClickListener = itemLongClickListener;
        return this;
    }


    public interface OnItemClickListener {
        public void onClick(int position);
    }


    public interface OnItemLongClickListener {
        public void onLongClick(int position);
    }

}
