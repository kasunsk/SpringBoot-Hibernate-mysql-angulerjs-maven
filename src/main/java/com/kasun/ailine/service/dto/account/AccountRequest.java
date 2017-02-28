package com.kasun.ailine.service.dto.account;

import com.kasun.ailine.service.model.account.Currency;

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
