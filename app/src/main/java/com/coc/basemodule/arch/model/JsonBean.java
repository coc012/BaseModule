package com.coc.basemodule.arch.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class JsonBean<T> implements Cloneable {
    /**
     * 返回状态码
     */
    private int code;
    /**
     * 消息说明
     */
    private String message;
    private String msg;
    private String pic;
    /**
     * 需要客户端本地存储的数据的最新版本号，格式目前为10位数字，即该数据最后更新的时间戳
     */
    private String version;
    /**
     * 数据对象
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRealMessage() {
        if (!TextUtils.isEmpty(message)) {
            return message;
        }

        if (!TextUtils.isEmpty(msg)) {
            return msg;
        }
        return "";
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @NonNull
    @Override
    public String toString() {
        return "JsonBean [code=" + code + ", message=" + message + ", version=" + version + ", data=" + data + "]";
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    protected T clone() throws CloneNotSupportedException {
        return (T) super.clone();
    }

}
