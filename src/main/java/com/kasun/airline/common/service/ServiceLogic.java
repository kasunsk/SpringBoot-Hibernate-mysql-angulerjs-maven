package com.kasun.airline.common.service;

/**
 * Created by kasun on 2/28/17.
 */
public interface ServiceLogic<T, V> {

    T invoke(V var);

}
