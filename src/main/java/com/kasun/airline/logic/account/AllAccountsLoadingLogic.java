package com.kasun.airline.logic.account;

import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.model.account.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class AllAccountsLoadingLogic extends StatelessServiceLogic<List<BankAccount>, String> {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    @Override
    public List<BankAccount> invoke(String applicantId) {

        validateInput(applicantId);
        return (accountDao.loadAccountByApplicantId(applicantId) == null ? new ArrayList<>() : accountDao
                .loadAccountByApplicantId(applicantId));
    }

    private void validateInput(String applicantId) {

        if (applicantId == null || applicantId.isEmpty()) {
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "Applicant id is null");
        }
    }
}
