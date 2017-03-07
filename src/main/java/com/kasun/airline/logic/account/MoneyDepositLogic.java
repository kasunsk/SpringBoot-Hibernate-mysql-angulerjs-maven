package com.kasun.airline.logic.account;

import com.kasun.airline.common.dto.CurrencyExchangeRequest;
import com.kasun.airline.common.dto.Price;
import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.service.account.AccountService;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class MoneyDepositLogic extends StatelessServiceLogic<BankAccount, DepositRequest> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Autowired
    private AccountService accountService;

    @Transactional
    @Override
    public BankAccount invoke(DepositRequest depositRequest) {

        validate(depositRequest);
        BankAccount account = accountHibernateDao.loadAccountByAccountNumber(depositRequest.getAccountId());
        Double newBalance = getNewBalance(depositRequest, account);
        account.setAvailableAmount(newBalance);
        accountHibernateDao.updateAccount(account);
        return account;
    }

    private void validate(DepositRequest depositRequest) {

        ValidationUtil.validate(depositRequest, "Deposit request is null");
        ValidationUtil.validate(depositRequest.getAccountId(), "Account id is null");
        ValidationUtil.validate(depositRequest.getPrice(), "Price is null");
        ValidationUtil.validate(depositRequest.getPrice().getCurrency(), "Currency is null");
        ValidationUtil.validate(depositRequest.getPrice().getPrice(), "Price amount is null");
    }

    private double getNewBalance(DepositRequest depositRequest, BankAccount account) {

        if (account == null) {
            throw new ServiceRuntimeException(ErrorCode.ACCOUNT_NOT_EXIST, "Invalid Account");
        }

        Price depositPrice = depositRequest.getPrice();

        //Provide currency exchange if necessary
        if (!account.getCurrency().equals(depositPrice.getCurrency())) {
            CurrencyExchangeRequest exchangeRequest = new CurrencyExchangeRequest();
            exchangeRequest.setTargetCurrency(account.getCurrency());
            exchangeRequest.setMonetaryAmount(depositPrice);
            depositPrice = accountService.exchangeCurrency(new ServiceRequest<>(exchangeRequest)).getPayload();
        }

        BigDecimal newBalance = BigDecimal.valueOf(account.getAvailableAmount()).add(BigDecimal
                .valueOf(depositPrice.getPrice()));

        return newBalance.doubleValue();
    }
}
