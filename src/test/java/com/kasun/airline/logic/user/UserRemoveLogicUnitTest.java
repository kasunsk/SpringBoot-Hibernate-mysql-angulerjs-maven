package com.kasun.airline.logic.user;


import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.user.UserDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserRemoveLogicUnitTest {

    @InjectMocks
    UserRemoveLogic logic = new UserRemoveLogic();

    @Mock
    UserDao userDao;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void removeFailTest() {
        logic.invoke(null);
    }

    @Test
    public void removeTest() {
        logic.invoke("23");
        verify(userDao, times(1)).remove("23");
    }
}
