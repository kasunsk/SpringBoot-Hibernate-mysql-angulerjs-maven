package com.crossover.techtrial.java.se.service.account;

import com.crossover.techtrial.java.se.dao.account.AccountDao;
import com.crossover.techtrial.java.se.dto.MoneyTransferRequest;
import com.crossover.techtrial.java.se.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kasun on 2/4/17.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Transactional
    public void createAccount(Account account) {
        accountDao.createAccount(account);
    }

    @Transactional
    public void deleteAccount(String accountNumber) {
        Account account = accountDao.loadAccountByAccountNumber(accountNumber);
        accountDao.deleteAccount(account);
    }

    @Transactional
    public Boolean transferMoney(MoneyTransferRequest request) {
        Account account = accountDao.loadAccountByAccountNumber(request.getAccountNumber());
        Double newAmount = 0d;
        if(request.getTransferType().equals(MoneyTransferRequest.TransferType.DEPOSIT)){
            newAmount = account.getAmount() + request.getTransferAmount();
        }else{
            newAmount = account.getAmount() - request.getTransferAmount();
        }
        if(newAmount >= 0){
            account.setAmount(newAmount);
        }else{
            return false;
        }
        accountDao.updateAccount(account);
        return true;
    }
}
