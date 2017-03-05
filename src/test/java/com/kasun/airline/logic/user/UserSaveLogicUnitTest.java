package com.kasun.airline.logic.user;

import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.model.user.User;
import com.kasun.airline.service.security.SecurityService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.fail;


public class UserSaveLogicUnitTest {

    @InjectMocks
    UserSaveLogic userSaveLogic = new UserSaveLogic();

    @Mock
    UserDao userHibernateDao;

    @Mock
    SecurityService securityService;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void userValidateNullTest() throws Exception {
        userSaveLogic.validateUser(null);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void userEmailValidateTest() throws Exception {
        User user = new User();
        userSaveLogic.validateUser(user);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void userNameValidateTest() throws Exception {
        User user = new User();
        user.setEmail("test@email.com");
        userSaveLogic.validateUser(user);
    }


    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void userPasswordValidateTest() throws Exception {
        User user = new User();
        user.setEmail("test@email.com");
        user.setName("name");
        userSaveLogic.validateUser(user);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void userAlreadyExistValidateTest() throws Exception {
        User user = new User();
        user.setEmail("test@email.com");
        user.setName("name");
        user.setPassword("password");

        when(userHibernateDao.loadUserByEmail("test@email.com")).thenReturn(new User());
        userSaveLogic.validateUser(user);
    }


    @Test
    public void userAlreadyExistValidateSuccessTest() throws Exception {
        User user = new User();
        user.setEmail("test@email.com");
        user.setName("name");
        user.setPassword("password");
        when(userHibernateDao.loadUserByEmail("test@email.com")).thenReturn(null);

        try {
            userSaveLogic.validateUser(user);
        } catch (ServiceRuntimeException ex) {
            fail();
        }
    }
}
