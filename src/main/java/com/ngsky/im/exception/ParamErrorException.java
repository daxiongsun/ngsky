package com.ngsky.im.exception;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/6/2018 10:52 PM
 **/
public class ParamErrorException extends BaseException {

    public ParamErrorException() {
        super(ErrorEnum.PARAM_ERROR);
    }

    public ParamErrorException(String message) {
        super(ErrorEnum.PARAM_ERROR, message);
    }

}
