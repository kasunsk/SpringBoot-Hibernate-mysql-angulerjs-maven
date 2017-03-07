package com.kasun.airline.logic.account;


import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.account.AccountHibernateDao;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.model.account.Currency;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

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

        BankAccount bankAccount = new BankAccount();
        bankAccount.setCurrency(Currency.AUD);
        when(accountHibernateDao.createAccount(bankAccount)).thenReturn(bankAccount);
        BankAccount resultAccount = logic.invoke(bankAccount);
        assertEquals(resultAccount, bankAccount);
    }
}
