package com.crossover.techtrial.java.se.dao.account;

import com.crossover.techtrial.java.se.model.account.Account;

/**
 * Created by kasun on 2/4/17.
 */
public interface AccountDao {

    Account loadAccountByApplicantId(String applicantId);

    Account loadAccountById(Long accountId);

    Account loadAccountByAccountNumber(String accountNumber);

    void updateAccount(Account applicantAccount);

    void createAccount(Account account);
    
    void deleteAccount(Account account);
}
