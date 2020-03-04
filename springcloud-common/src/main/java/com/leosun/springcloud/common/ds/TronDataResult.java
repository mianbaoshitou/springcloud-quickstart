package com.leosun.springcloud.common.ds;


/**
 * @program: linked-base-leosun
 * @description: 统一应答信息结构
 * @author: Yueling
 * @create: 2019-12-06 15:23
 **/
public class TronDataResult {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 应答码
     */
    private String code;
    /**
     * 提示信息
     */
    private String msg;

    /**
     * 结果对象
     */
    private Object data;
    public TronDataResult(TronDataResultCode tronDataResultCode){
        init(tronDataResultCode);
    }

    private void init(TronDataResultCode tronDataResultCode){
        this.success = tronDataResultCode.success;
        this.code = tronDataResultCode.code;
        this.msg = tronDataResultCode.msg;
    }
    public TronDataResult(TronDataResultCode tronDataResultCode, Object data){
        init(tronDataResultCode);
        this.data = data;
    }

    public void fillDat(Object data){
        this.data = data;
    }

    public Object getData(){
        return  this.data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public TronDataResult() {
    }
}
