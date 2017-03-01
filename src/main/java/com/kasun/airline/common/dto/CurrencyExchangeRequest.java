package com.kasun.airline.common.dto;

import com.kasun.airline.model.account.Currency;

/**
 * Created by kasun on 3/1/17.
 */
public class CurrencyExchangeRequest {

    private Price monetaryAmount;
    private Currency targetCurrency;

    public Price getMonetaryAmount() {
        return monetaryAmount;
    }

    public void setMonetaryAmount(Price monetaryAmount) {
        this.monetaryAmount = monetaryAmount;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
