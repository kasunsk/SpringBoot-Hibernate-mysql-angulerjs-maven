package com.kasun.airline.common.execption;

/**
 * This class provide standard error codes for the application
 */
public interface ErrorCode {

    String NO_ENOUGH_INV = "No enough inventory";
    String INVALID_OFFER_ROUT = "Invalid offer route";
    String NOT_ENOUGH_CREDIT = "Credit not enough";
    String ROUTE_ALREADY_EXIST = "Rout already exist";
    String CAN_NOT_CONVERT_CURRENCY = "Can not convert currency";
    String USER_NOT_FOUND = "User not found";
    String EMAIL_SENDING_FAIL = "Email sending fail";
}