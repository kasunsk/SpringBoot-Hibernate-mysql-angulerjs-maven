package com.kasun.airline.dao.account;

import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.model.user.UserTicket;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AccountDao {

    List<BankAccount> loadAccountByApplicantId(String applicantId);

    BankAccount loadAccountById(Long accountId);

    BankAccount loadAccountByAccountNumber(String accountNumber);

    void updateAccount(BankAccount applicantBankAccount);

    BankAccount createAccount(BankAccount bankAccount);
    
    void deleteAccount(BankAccount bankAccount);

    void saveUserTicket(UserTicket userTicket);

    void removeAccount(String accountId);
}
