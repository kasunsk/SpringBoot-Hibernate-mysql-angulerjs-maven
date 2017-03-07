package com.kasun.airline.logic.account;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * This class is responsible for providing all the logic for bank account creation.
 */
@Component
public class AccountCreateLogic extends StatelessServiceLogic<BankAccount, BankAccount> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Transactional
    @Override
    public BankAccount invoke(BankAccount bankAccount) {
        validateAccount(bankAccount);
        BankAccount account = accountHibernateDao.createAccount(bankAccount);

        if (account != null && account.getUser() != null) {
            account.getUser().setUserBankAccounts(null);
        }
        return account;
    }

    private void validateAccount(BankAccount bankAccount) {

        ValidationUtil.validate(bankAccount, "Bank account is null");
        ValidationUtil.validate(bankAccount.getCurrency(), "Currency is null");

        if (bankAccount.getAvailableAmount() == null) {
            bankAccount.setAvailableAmount(0D);
        }
    }

}
