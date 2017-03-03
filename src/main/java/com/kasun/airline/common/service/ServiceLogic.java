package com.kasun.airline.common.service;

/**
 * Basic service logic function provider.
 */
public interface ServiceLogic<T, V> {

    T invoke(V var);

}
