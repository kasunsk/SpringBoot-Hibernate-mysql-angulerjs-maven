package com.kasun.airline.dto.account;

import com.kasun.airline.model.account.Currency;

/**
 * Created by kasun on 2/6/17.
 */
public class AccountRequest {

    private Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
