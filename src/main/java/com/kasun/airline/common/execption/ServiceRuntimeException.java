package com.kasun.airline.common.execption;

/**
 * This class wrap exceptions
 */
public class ServiceRuntimeException extends RuntimeException{

    private String errorMsg;
    private String errorCode;

    public ServiceRuntimeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ServiceRuntimeException(String errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
