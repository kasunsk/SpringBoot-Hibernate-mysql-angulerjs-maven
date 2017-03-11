package com.kasun.airline.logic.account;

import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.kasun.airline.util.ValidationUtil.validate;

@Component
public class AccountRemoveLogic extends StatelessServiceLogic<Void, String> {

    @Autowired
    private AccountDao accountHibernateDao;

    @Transactional
    @Override
    public Void invoke(String accountId) {

        validate(accountId, "Invalid account Id");
        accountHibernateDao.removeAccount(accountId);
        return new Void();
    }
}
