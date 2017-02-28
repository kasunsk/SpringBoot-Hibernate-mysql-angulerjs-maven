package com.kasun.airline.dto.account;

import com.kasun.airline.common.dto.Price;

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
