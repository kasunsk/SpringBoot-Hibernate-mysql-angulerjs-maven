package com.kasun.airline.logic.account;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.model.account.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class AllAccountsLoadingLogic extends StatelessServiceLogic<List<BankAccount>, String> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Transactional
    @Override
    public List<BankAccount> invoke(String applicantId) {
        return accountHibernateDao.loadAccountByApplicantId(applicantId);
    }
}
