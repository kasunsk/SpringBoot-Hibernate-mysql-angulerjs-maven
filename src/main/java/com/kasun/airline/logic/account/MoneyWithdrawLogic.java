package com.kasun.airline.logic.account;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class MoneyWithdrawLogic extends StatelessServiceLogic<BankAccount, DepositRequest> {

    @Transactional
    @Override
    public BankAccount invoke(DepositRequest var) {
        return null;
    }
}
