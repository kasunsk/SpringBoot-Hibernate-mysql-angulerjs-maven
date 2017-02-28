package com.crossover.techtrial.java.se.service.user;

import com.crossover.techtrial.java.se.dao.user.UserDao;
import com.crossover.techtrial.java.se.model.user.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.fail;

/**
 * Created by kasun on 2/7/17.
 */
public class UserServiceUnitTest {

    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();

    @Mock
    UserDao userHibernateDao;

    @BeforeMethod(alwaysRun=true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void authenticateUserFailTest() {

        when(userHibernateDao.loadUserById("2")).thenReturn(null);
        userService.authenticateUser("2");
    }

    @Test
    public void authenticateUserTest() {

        when(userHibernateDao.loadUserById("2")).thenReturn(new User());

        try {
            userService.authenticateUser("2");
        } catch (RuntimeException ex) {
            fail();
        }
    }

}
