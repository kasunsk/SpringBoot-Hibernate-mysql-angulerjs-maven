package com.kasun.airline.logic.account;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class MoneyDepositLogic extends StatelessServiceLogic<BankAccount, DepositRequest> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Transactional
    @Override
    public BankAccount invoke(DepositRequest depositRequest) {
        BankAccount account = accountHibernateDao.loadAccountByAccountNumber(depositRequest.getAccountId());
        Double newBalance = account.getAvailableAmount() + depositRequest.getPrice().getPrice();
        account.setAvailableAmount(newBalance);
        accountHibernateDao.updateAccount(account);
        return account;
    }
}
