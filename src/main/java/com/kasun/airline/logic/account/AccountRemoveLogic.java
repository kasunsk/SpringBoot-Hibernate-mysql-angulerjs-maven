package com.kasun.airline.logic.account;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AccountRemoveLogic extends StatelessServiceLogic<com.kasun.airline.common.dto.Void, String> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Transactional
    @Override
    public com.kasun.airline.common.dto.Void invoke(String accountId) {
        accountHibernateDao.removeAccount(accountId);
        return new Void();
    }
}
