package com.crossover.techtrial.java.se.common.dto;

import com.crossover.techtrial.java.se.model.account.Currency;

/**
 * Created by kasun on 2/4/17.
 */
public class Price {

    private Double price;
    private Currency currency;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
