package com.crossover.techtrial.java.se.common.service;

/**
 * Created by kasun on 2/28/17.
 */
public interface ServiceLogic<T,V> {

    public abstract V invoke(T request);

}
