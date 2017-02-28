package com.crossover.techtrial.java.se.dto.account;

import com.crossover.techtrial.java.se.common.dto.Price;

/**
 * Created by kasun on 2/7/17.
 */
public class DepositRequest {

    private String accountId;

    private Price price;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
