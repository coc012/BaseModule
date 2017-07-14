package com.coc.basemodule.arch.model;

import android.os.Environment;

import java.io.File;

/**
 * Module配置文件
 * 主要配置Module涉及到的一些静态配置(包括但不限于)
 * 1.静态不变的网址，比如图片服务器
 * 2.文件目录
 * 3.静态Key
 */
public class NetConfig {
    public static final String ImageUploadPath = "https://upload.xin.com/upload.php";

    public static final String BASEURL_RELEASE = "https://api.youxinche.com/youxintong/v2/";
    public static final String BASEURL_DEBUG = "http://api.test.youxinche.com/youxintong/v2/";
    public static final String BASEURL_DEV = "http://develop.api.test.youxinche.com/youxintong/v2/";
    public static final String BASEURL_MOCK = "http://rap.taobao.org/mockjs/15270/youxintong/v2/";

    //图片上传
    public static final String APP = "carpro_app";
    public static final String KEY = "rQyw1flWjZDxmZFF";
    public static final String NET_BASE_URL = "http://rap.taobao.org/mockjs/11497/";//测试服:正式服
    public static final String IMG_PREFIX = "http://c1.xinstatic.com";
    public static final String UpdatePath = "http://app.xin.com/api/package";
    public static final String UpdatePathDebug = "http://app.test.xin.com/api/package";


    //相机拍照路径  /uxin/newcar2b/camera
    public static final String DIR_CAMERA_PATH = Environment.getExternalStorageDirectory() + File.separator + "uxin" + File.separator + "newcar2b" + File.separator + "camera";

    public static final String CACHE_DIR_PATH = Environment.getExternalStorageDirectory() + File.separator + "uxin" + File.separator + "newcar2b" + File.separator + "cache";
    //人脸识别 保存图片露肩
    public static final String LIVE_DIR_PATH = Environment.getExternalStorageDirectory() + File.separator + "uxin" + File.separator + "newcar2b" + File.separator + "Liveness";

}
