package com.kasun.airline.dto.account;

import com.kasun.airline.common.dto.Price;

/**
 * Created by kasun on 2/6/17.
 */
public class AccountResponse {

    private String accountId;

    private Price availableAmount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Price getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Price availableAmount) {
        this.availableAmount = availableAmount;
    }
}
