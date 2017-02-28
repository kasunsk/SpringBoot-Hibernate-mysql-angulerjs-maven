package com.kasun.airline.common.service;

/**
 * Created by kasun on 2/28/17.
 */
public abstract class  StatelessServiceLogic<T,V> implements ServiceLogic<T,V> {

    public abstract T invoke(V var);
}
