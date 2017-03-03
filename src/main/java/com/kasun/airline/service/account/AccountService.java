package com.kasun.airline.service.account;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.dto.account.MoneyTransferRequest;
import com.kasun.airline.model.account.Currency;

import java.util.List;

/**
 * This API provide all the service method for Account Service
 */
public interface AccountService {

    /**
     *
     * @param bankAccount
     * @return Created BankAccount
     */
    ServiceResponse<BankAccount> createAccount(ServiceRequest<BankAccount> bankAccount);

    /**
     *
     * @param accountNumber
     * @return void response
     */
    ServiceResponse<com.kasun.airline.common.dto.Void> deleteAccount(ServiceRequest<String> accountNumber);

    /**
     *
     * @param depositRequest
     * @return updated BankAccount
     */
    ServiceResponse<BankAccount> deposit(ServiceRequest<DepositRequest> depositRequest);

    /**
     *
     * @param withdrawRequest
     * @return updated BankAccount
     */
    ServiceResponse<BankAccount> withdraw(ServiceRequest<DepositRequest> withdrawRequest);

    /**
     *
     * @param moneyTransferRequest
     * @return Boolean
     */
    ServiceResponse<Boolean> transferMoney(ServiceRequest<MoneyTransferRequest> moneyTransferRequest);

    /**
     *
     * @param applicantId
     * @return List of all BankAccount of applicant
     */
    ServiceResponse<List<BankAccount>> loadAllAccounts(ServiceRequest<String> applicantId);

    /**
     *
     * @param accountId
     * @return void
     */
    ServiceResponse<Void> removeAccount(ServiceRequest<String> accountId);

    /**
     *
     * @param exchangeRequest
     * @return Price object after money exchanged
     */
    ServiceResponse<Price> exchangeCurrency(ServiceRequest<CurrencyExchangeRequest> exchangeRequest);

}
