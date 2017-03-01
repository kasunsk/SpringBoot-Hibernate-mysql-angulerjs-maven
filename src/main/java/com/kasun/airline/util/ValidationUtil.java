package com.kasun.airline.util;

import com.kasun.airline.common.execption.ServiceRuntimeException;

import javax.xml.ws.Service;

/**
 * Created by kasun on 2/6/17.
 */
public class ValidationUtil {

    public static void validate(String str, String massage) {

        if (str == null || str.isEmpty()) {
            throw new ServiceRuntimeException(massage);
        }
    }

    public static void validate(Object str, String massage) {

        if (str == null) {
            throw new ServiceRuntimeException(massage);
        }
    }
}
