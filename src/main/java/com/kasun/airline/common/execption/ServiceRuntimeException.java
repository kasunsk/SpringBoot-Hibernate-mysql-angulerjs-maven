package com.kasun.airline.common.execption;

/**
 * Created by kasun on 2/27/17.
 */
public class ServiceRuntimeException extends RuntimeException{

    private String errorMsg;
    private String errorCode;

    public ServiceRuntimeException(String errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
