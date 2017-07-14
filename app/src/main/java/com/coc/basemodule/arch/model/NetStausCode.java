package com.coc.basemodule.arch.model;

/**
 * Created by tang on 2017/3/20.
 */

public class NetStausCode {

    public static final int CODE_UPLOAD_PIC_SUCCESS = 1;//非法请求

    public static final int CODE_REQUES_ILLEGAL = 100001;//非法请求
    public static final int CODE_ERROR_PRAMA = 100002;//参数错误
    public static final int CODE_SUCCESS = 200000;//成功
    public static final int CODE_SUCCESS_EMPTY = 200001;//未能获取到数据
    public static final int CODE_FAILURE = 200101;//失败
    public static final int CODE_PAGE_MOVE = 300002;//地址要临时转移到指定页面
    public static final int CODE_NOT_FOUND = 300010;//请求的资源不存在
    public static final int CODE_NEED_LOGIN = 400000;//登录失败/未登录
    public static final int CODE_UNAUTHORIZED = 400001;//未授权
    public static final int CODE_LOGIN_ERROR = 400002;//用户名或密码错误
    public static final int CODE_USER_FORBID = 400003;//用户被禁止访问
    public static final int CODE_USER_NOEXIST = 400004;//用户名不存在
    public static final int CODE_VERIFYCODE_ERROR = 400005;//验证码错误
    public static final int CODE_PASSWORD_CHANGE_FAILURE = 400006;//密码修改失败
    public static final int CODE_NOHOST = 400007;//没有坐席,请先添加！
    public static final int CODE_SERVER_ERROR = 500000;//服务器异常
    public static final int CODE_SERVER_BUSY = 500003;//服务器忙
    public static final int CODE_SERVER_TIMEOUT = 500004;//请求超时
    public static final int CODE_NEED_VERIFY = 400008;//报价管理 弹出语言验证码

    public static final int CODE_FORCE_UPDATE = 400009;//强制更新 状态码 需要跳转splash页面进行更新检查


}
