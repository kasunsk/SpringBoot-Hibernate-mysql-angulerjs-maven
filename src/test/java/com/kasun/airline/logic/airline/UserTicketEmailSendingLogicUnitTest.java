package com.kasun.airline.logic.airline;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.dao.email.EmailDao;
import com.kasun.airline.model.email.EmailModel;
import com.kasun.airline.model.user.User;
import com.kasun.airline.model.user.UserTicket;
import com.kasun.airline.service.email.EmailService;
import com.kasun.airline.service.user.UserService;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by kasun on 3/8/17.
 */
public class UserTicketEmailSendingLogicUnitTest {

    @InjectMocks
    UserTicketEmailSendingLogic logic = new UserTicketEmailSendingLogic();

    @Mock
    private EmailDao emailHibernateDao;

    @Mock
    private AirlineDao airlineHibernateDao;

    @Mock
    private UserService userService;

    @Mock
    private EmailService emailService;


    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void invalidUserTicketIdTest() {
        logic.invoke(null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void emailSendSuccessTest() {

        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(25L);
        when(airlineHibernateDao.loadUserTicketById(12L)).thenReturn(userTicket);

        ServiceResponse<User> userResponse = new ServiceResponse<>();
        User user = new User();
        user.setEmail("test@gmail.com");
        userResponse.setPayload(user);
        when(userService.loadUserById(Matchers.<ServiceRequest>any())).thenReturn(userResponse);
        Boolean result = logic.invoke("12");
        verify(emailService, times(1)).sendEmail(Matchers.<ServiceRequest>any());
        verify(emailHibernateDao, times(1)).saveEmailData(Matchers.<EmailModel>any());
        assertTrue(result);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void emailSendFailTest() {

        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(25L);
        when(airlineHibernateDao.loadUserTicketById(12L)).thenReturn(userTicket);

        ServiceResponse<User> userResponse = new ServiceResponse<>();
        User user = new User();
        user.setEmail("test@gmail.com");
        userResponse.setPayload(user);
        when(userService.loadUserById(Matchers.<ServiceRequest>any())).thenReturn(userResponse);
        when(emailService.sendEmail(Matchers.<ServiceRequest>any())).thenThrow(new ServiceRuntimeException("Error sending mail"));
        assertFalse(logic.invoke("12"));
        verify(emailHibernateDao, times(1)).saveEmailData(Matchers.<EmailModel>any());
    }

}
