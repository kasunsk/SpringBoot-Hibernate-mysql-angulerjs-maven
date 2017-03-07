package com.kasun.airline.logic.account;

import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.account.AccountHibernateDao;
import com.kasun.airline.model.account.BankAccount;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AllAccountsLoadingLogicUnitTest {

    @InjectMocks
    AllAccountsLoadingLogic logic = new AllAccountsLoadingLogic();

    @Mock
    AccountHibernateDao accountDao;

    @BeforeMethod(alwaysRun=true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void validateInputNullTest() {
        logic.invoke(null);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void validateInputEmptyTest() {
        logic.invoke("");
    }

    @Test
    public void validateTest() {
        try {
            logic.invoke("applicantId");
            when(accountDao.loadAccountByApplicantId("applicantId")).thenReturn(new ArrayList<>());
        } catch (ServiceRuntimeException ex) {
            fail();
        }
    }

    @Test
    public void invokeTest() {

        List<BankAccount> accounts = new ArrayList<>();
        when(accountDao.loadAccountByApplicantId("applicantId")).thenReturn(accounts);
        assertEquals(accounts, logic.invoke("applicantId"));
    }

    @Test
    public void invokeNullTest() {

        when(accountDao.loadAccountByApplicantId("applicantId")).thenReturn(null);
        assertEquals(logic.invoke("applicantId").size(), 0);
    }
}
