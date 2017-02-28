package com.kasun.airline.common.dto;

import com.kasun.airline.model.account.Currency;

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
