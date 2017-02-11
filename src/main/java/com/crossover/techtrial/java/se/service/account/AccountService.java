package com.crossover.techtrial.java.se.service.account;

import com.crossover.techtrial.java.se.dto.MoneyTransferRequest;
import com.crossover.techtrial.java.se.model.account.Account;

/**
 * Created by kasun on 2/4/17.
 */
public interface AccountService {

    void createAccount(Account account);

    void deleteAccount(String accountNumber);

    Boolean transferMoney(MoneyTransferRequest moneyTransferRequest);

}
