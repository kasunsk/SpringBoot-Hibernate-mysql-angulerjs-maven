package com.crossover.techtrial.java.se.dto.account;

import com.crossover.techtrial.java.se.model.account.Currency;

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
