package com.kasun.airline.logic.account;


import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.account.AccountHibernateDao;
import com.kasun.airline.model.account.BankAccount;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountCreateLogicUnitTest  {

    @InjectMocks
    AccountCreateLogic logic = new AccountCreateLogic();

    @Mock
    AccountHibernateDao accountHibernateDao;

    @BeforeMethod(alwaysRun=true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void validateAccountNullTest() {
        logic.invoke(null);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void validateCurrencyNullTest() {
        logic.invoke(new BankAccount());
    }

    @Test
    public void invokeTest() {


        logic.invoke(new BankAccount());
    }
}
