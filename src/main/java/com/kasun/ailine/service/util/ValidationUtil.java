package com.kasun.ailine.service.util;

/**
 * Created by kasun on 2/6/17.
 */
public class ValidationUtil {

    public static void validate(String str, String massage) {

        if (str == null || str.isEmpty()) {
            throw new RuntimeException(massage);
        }
    }

    public static void validate(Object str, String massage) {

        if (str == null) {
            throw new RuntimeException(massage);
        }
    }
}
