package com.kasun.airline.logic.account;

import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.model.account.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class AccountDeleteLogic extends StatelessServiceLogic<com.kasun.airline.common.dto.Void, String> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Transactional
    @Override
    public Void invoke(String accountNumber) {
        BankAccount bankAccount = accountHibernateDao.loadAccountByAccountNumber(accountNumber);
        accountHibernateDao.deleteAccount(bankAccount);
        return new Void();
    }
}
