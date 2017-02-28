package com.kasun.airline.service.account;

import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.dto.account.MoneyTransferRequest;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AccountService {

    BankAccount createAccount(BankAccount bankAccount);

    void deleteAccount(String accountNumber);

    BankAccount deposit(DepositRequest depositRequest);

    BankAccount withdraw(DepositRequest depositRequest);

    Boolean transferMoney(MoneyTransferRequest moneyTransferRequest);

    List<BankAccount> loadAllAccounts(String applicantId);

    void removeAccount(String accountId);
}
