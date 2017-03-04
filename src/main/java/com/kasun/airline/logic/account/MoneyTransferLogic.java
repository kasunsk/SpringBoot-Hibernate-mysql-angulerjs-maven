package com.kasun.airline.logic.account;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.dto.account.MoneyTransferRequest;
import com.kasun.airline.model.account.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MoneyTransferLogic extends StatelessServiceLogic<Boolean, MoneyTransferRequest> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Transactional
    @Override
    public Boolean invoke(MoneyTransferRequest request) {

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
}
