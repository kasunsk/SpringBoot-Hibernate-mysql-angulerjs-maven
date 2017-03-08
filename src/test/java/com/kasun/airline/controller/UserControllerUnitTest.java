package com.kasun.airline.controller;


import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.common.dto.UserSearchCriteria;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dto.user.UserRole;
import com.kasun.airline.model.user.User;
import com.kasun.airline.service.user.UserService;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class UserControllerUnitTest {

    @InjectMocks
    UserController controller = new UserController();

    @Mock
    UserService userService;

    @Mock
    MessageSource messageSource;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void removeUserTest() {
        controller.removeUser("test");
        verify(userService, times(1)).removeUser(Matchers.<ServiceRequest>any());
    }

    @SuppressWarnings("unchecked")
    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void searchUsersFailTest() {

        User user = new User();
        user.setRole(UserRole.USER);
        ServiceResponse<User> userServiceResponse = new ServiceResponse<>();
        userServiceResponse.setPayload(user);
        when(userService.loadUserById(Matchers.<ServiceRequest>any())).thenReturn(userServiceResponse);

        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        controller.searchUser("12", searchCriteria);
        verify(userService, times(0)).searchUser(any());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void searchUserTest() {

        User user = new User();
        user.setRole(UserRole.ADMIN);
        ServiceResponse<User> userServiceResponse = new ServiceResponse<>();
        userServiceResponse.setPayload(user);
        when(userService.loadUserById(Matchers.<ServiceRequest>any())).thenReturn(userServiceResponse);

        ServiceResponse<List<User>> userListResponse = new ServiceResponse<>();
        List<User> users = new ArrayList<>();
        userListResponse.setPayload(users);
        when(userService.searchUser(Matchers.<ServiceRequest>any())).thenReturn(userListResponse);
        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        assertEquals(users, controller.searchUser("12", searchCriteria));
    }


}
