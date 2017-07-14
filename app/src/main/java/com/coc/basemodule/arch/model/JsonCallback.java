package com.coc.basemodule.arch.model;

/**
 * Created by tang on 2017/1/7.
 */


public abstract class JsonCallback<T> {

    /**
     * 请求网络开始前，UI线程
     */
    public void onStart() {
    }

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(JsonBean<T> jsonBean, String result);

    /**
     * 缓存成功的回调,UI线程
     */
    public void onCacheSuccess() {
    }

    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    public void onError(Throwable e, String msg) {
    }

    public void onEmpty(String msg) {
    }


    /**
     * 缓存失败的回调,UI线程
     */
    public void onCacheError(Throwable e, String msg) {
    }


    /**
     * 请求网络结束后，UI线程
     */
    public void onEnd() {
    }

    /**
     * Post执行上传过程中的进度回调，get请求不回调，UI线程
     *
     * @param currentSize  当前上传的字节数
     * @param totalSize    总共需要上传的字节数
     * @param progress     当前上传的进度
     * @param networkSpeed 当前上传的速度 字节/秒
     */
    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
    }

    /**
     * 执行下载过程中的进度回调，UI线程
     *
     * @param currentSize  当前下载的字节数
     * @param totalSize    总共需要下载的字节数
     * @param progress     当前下载的进度
     * @param networkSpeed 当前下载的速度 字节/秒
     */
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
    }
}