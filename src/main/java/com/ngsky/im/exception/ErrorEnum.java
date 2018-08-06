package com.ngsky.im.exception;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/4/2018 5:36 PM
 **/
public enum ErrorEnum implements ErrorCode {

    UNKNOWN(1000, "未知错误"),
    BIND_PORT_FAILURE(1001, "绑定端口失败"),
    INIT_FAILURE(1002, "初始化服务器失败"),
    UNSUPPORT_TYPE(1003, "不支持此消息类型"),

    PARAM_ERROR(2000, "参数错误"),
    MESSAGE_FORMATE_ERROR(2001, "数据格式不正确");

    private Integer code;
    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
