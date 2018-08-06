package com.ngsky.im.exception;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/4/2018 5:23 PM
 **/
public abstract class BaseException extends RuntimeException implements ErrorCode {
   private final ErrorCode errorCode;

   public BaseException(ErrorCode errorCode){
       this.errorCode = errorCode;
   }

   public BaseException(ErrorCode errorCode, String message){
       super(message);
       this.errorCode = errorCode;
   }

    public BaseException(ErrorCode errorCode, String message, Throwable cause){
        super(message, cause);
        this.errorCode = errorCode;
    }

    @Override
    public Integer getCode() {
        return errorCode != null ? errorCode.getCode() : null;
    }

    @Override
    public String getMessage() {
        return StringUtils.isBlank(super.getMessage()) && errorCode != null ? errorCode.getMessage(): super.getMessage();
    }

    public ErrorCode getErrorCode(){
       return errorCode;
    }
}
