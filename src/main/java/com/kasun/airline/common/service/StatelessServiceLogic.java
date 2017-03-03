package com.kasun.airline.common.service;

/**
 *  Usage of polymorphism in OOP for every logic classes.
 */
public abstract class  StatelessServiceLogic<T,V> implements ServiceLogic<T,V> {

    public abstract T invoke(V var);
}
