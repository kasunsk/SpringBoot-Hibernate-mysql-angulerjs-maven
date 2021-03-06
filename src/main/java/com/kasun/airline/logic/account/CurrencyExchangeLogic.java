package com.kasun.airline.logic.account;

import com.kasun.airline.common.dto.CurrencyExchangeRequest;
import com.kasun.airline.common.dto.Price;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.model.account.Currency;
import com.kasun.airline.util.CurrencyConverter;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class CurrencyExchangeLogic extends StatelessServiceLogic<Price, CurrencyExchangeRequest> {

    @Transactional
    @Override
    public Price invoke(CurrencyExchangeRequest exchangeRequest) {

        validateRequest(exchangeRequest);
        Price amount = exchangeRequest.getMonetaryAmount();
        Currency toCurrency = exchangeRequest.getTargetCurrency();
        Currency fromCurrencyCode = amount.getCurrency();
        double conversionRate = CurrencyConverter.convert(fromCurrencyCode.toString(), toCurrency.toString());
        double convertedAmount = conversionRate * amount.getPrice();
        return buildConvertedPrice(toCurrency, convertedAmount);
    }

    private void validateRequest(CurrencyExchangeRequest exchangeRequest) {

        ValidationUtil.validate(exchangeRequest, "Request is null");
        ValidationUtil.validate(exchangeRequest.getMonetaryAmount(), "Price is null");
        ValidationUtil.validate(exchangeRequest.getTargetCurrency(), "Target currency is null");
    }


    private Price buildConvertedPrice(Currency toCurrency, double convertedAmount) {
        Price convertedPrice = new Price();
        convertedPrice.setPrice(convertedAmount);
        convertedPrice.setCurrency(toCurrency);
        return convertedPrice;
    }
}
