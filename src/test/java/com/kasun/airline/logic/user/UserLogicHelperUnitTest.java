package com.kasun.airline.logic.user;


import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.model.user.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserLogicHelperUnitTest {

    @InjectMocks
    UserLogicHelper helper = new UserLogicHelper();

    @Mock
    UserDao userHibernateDao;

    @BeforeMethod(alwaysRun=true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void loadUserFailTest() {

        String email = "test@test.com";
        when(userHibernateDao.loadUserByEmail(email)).thenReturn(null);
        helper.loadUser(email);
    }

    @Test
    public void loadUserTest() {

        String email = "test@test.com";
        User expected = new User();
        when(userHibernateDao.loadUserByEmail(email)).thenReturn(expected);
        User result = helper.loadUser(email);
        assertEquals(result, expected);
    }
}
