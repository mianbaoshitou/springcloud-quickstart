package com.leosun.springcloud.common.ds;

/**
 * @program: linked-base-leosun
 * @description: 应答码
 * 前两位为大类编号，相邻一组偶奇数为一组。例如 10为http官方应答码，11为http应答码补充部分。
 * @author: Yueling
 * @create: 2019-12-06 15:24
 **/
public enum TronDataResultCode {
    SUCCESS(true,           "00000", "成功"),
    // HTTP 相应部分应答码
    UNAUTHENTICATED(false,  "10401","未登录"),
    UNAUTHORISE(false,      "11001","权限不足"),

    UNKONW_ERROR(false,     "999999","未知错误")

    ;


    /**
     * 是否成功
     */
     boolean success;
    /**
     * 应答码
     */
    String code;
    /**
     * 提示信息
     */
    String msg;

    TronDataResultCode(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
}
