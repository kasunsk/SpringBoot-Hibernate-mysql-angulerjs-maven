package com.kasun.airline.logic.account;

import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.account.AccountHibernateDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AccountRemoveLogicUnitTest {

    @InjectMocks
    AccountRemoveLogic logic = new AccountRemoveLogic();

    @Mock
    AccountHibernateDao accountHibernateDao;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void accountRemoveFailTest() {
        logic.invoke(null);
    }

    @Test
    public void accountRemoveTest() {
        logic.invoke("1");
        verify(accountHibernateDao, times(1)).removeAccount("1");
    }
}
