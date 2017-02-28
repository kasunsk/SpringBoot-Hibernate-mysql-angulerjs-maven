package com.crossover.techtrial.java.se.service.account;

import com.crossover.techtrial.java.se.common.dto.Price;
import com.crossover.techtrial.java.se.dao.account.AccountDao;
import com.crossover.techtrial.java.se.dto.account.AccountResponse;
import com.crossover.techtrial.java.se.dto.account.DepositRequest;
import com.crossover.techtrial.java.se.model.account.BankAccount;
import com.crossover.techtrial.java.se.dto.account.MoneyTransferRequest;
import com.crossover.techtrial.java.se.model.user.User;
import com.crossover.techtrial.java.se.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountHibernateDao;

    @Autowired
    UserService userService;

    @Transactional
    @Override
    public BankAccount createAccount(BankAccount bankAccount) {

        validateAccount(bankAccount);
        BankAccount account = accountHibernateDao.createAccount(bankAccount);
        makeBankAccountRespons(account);
        return account;
    }

    private void makeBankAccountRespons(BankAccount account) {

        //account.getUser().setPassword(null);
        account.getUser().setUserBankAccounts(null);
    }

    private void validateAccount(BankAccount bankAccount) {
        if (bankAccount.getAvailableAmount() == null) {
            bankAccount.setAvailableAmount(0D);
        }
    }

    @Transactional
    @Override
    public void deleteAccount(String accountNumber) {

        BankAccount bankAccount = accountHibernateDao.loadAccountByAccountNumber(accountNumber);
        accountHibernateDao.deleteAccount(bankAccount);
    }

    @Transactional
    @Override
    public BankAccount deposit(DepositRequest depositRequest) {

        BankAccount account = accountHibernateDao.loadAccountByAccountNumber(depositRequest.getAccountId());
        Double newBalance = account.getAvailableAmount() + depositRequest.getPrice().getPrice();
        account.setAvailableAmount(newBalance);
        accountHibernateDao.updateAccount(account);
        return account;
    }


    @Transactional
    @Override
    public BankAccount withdraw(DepositRequest depositRequest) {
        return null;
    }

    @Transactional
    @Override
    public Boolean transferMoney(MoneyTransferRequest request) {

        BankAccount bankAccount = accountHibernateDao.loadAccountByAccountNumber(request.getAccountNumber());
        Double newAmount = 0d;

        if (request.getTransferType().equals(MoneyTransferRequest.TransferType.DEPOSIT)) {
            newAmount = bankAccount.getAvailableAmount() + request.getTransferAmount();

        } else {
            newAmount = bankAccount.getAvailableAmount() - request.getTransferAmount();
        }

        if (newAmount >= 0) {
            bankAccount.setAvailableAmount(newAmount);
        } else {
            return false;
        }

        accountHibernateDao.updateAccount(bankAccount);
        return true;
    }

    @Override
    @Transactional
    public List<BankAccount> loadAllAccounts(String applicantId) {
        //User user = userService.loadUserById(applicantId);
        return accountHibernateDao.loadAccountByApplicantId(applicantId);
        //return user.getUserBankAccounts();
    }

    @Transactional
    @Override
    public void removeAccount(String accountId) {

        accountHibernateDao.removeAccount(accountId);

    }

    private AccountResponse getAccountResponse(BankAccount acc) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(acc.getAccountId().toString());
        Price price = getPrice(acc);
        accountResponse.setAvailableAmount(price);
        return accountResponse;
    }

    private Price getPrice(BankAccount acc) {
        Price price = new Price();
        price.setCurrency(acc.getCurrency());
        price.setPrice(acc.getAvailableAmount());
        return price;
    }
}
