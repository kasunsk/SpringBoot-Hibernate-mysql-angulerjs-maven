package com.kasun.airline.service.account;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.dto.account.MoneyTransferRequest;
import com.kasun.airline.model.account.Currency;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AccountService {

    ServiceResponse<BankAccount> createAccount(ServiceRequest<BankAccount> bankAccount);

    ServiceResponse<com.kasun.airline.common.dto.Void> deleteAccount(ServiceRequest<String> accountNumber);

    ServiceResponse<BankAccount> deposit(ServiceRequest<DepositRequest> depositRequest);

    ServiceResponse<BankAccount> withdraw(ServiceRequest<DepositRequest> withdrawRequest);

    ServiceResponse<Boolean> transferMoney(ServiceRequest<MoneyTransferRequest> moneyTransferRequest);

    ServiceResponse<List<BankAccount>> loadAllAccounts(ServiceRequest<String> applicantId);

    ServiceResponse<Void> removeAccount(ServiceRequest<String> accountId);

    ServiceResponse<Price> exchangeCurrency(ServiceRequest<CurrencyExchangeRequest> exchangeRequest);

}
