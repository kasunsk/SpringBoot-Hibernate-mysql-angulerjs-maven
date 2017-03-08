package com.kasun.airline.logic.airline;


import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.model.user.UserTicket;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ApplicantTicketsRetrieveLogicUnitTest {

    @InjectMocks
    ApplicantTicketsRetrieveLogic logic = new ApplicantTicketsRetrieveLogic();

    @Mock
    private AirlineDao airlineDao;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void retrieveFailTest() {
        logic.invoke(null);
    }

    @Test
    public void retrieveTest() {

        List<UserTicket> userTickets = new ArrayList<>();
        when(airlineDao.loadApplicantAirlineOffers(12L)).thenReturn(userTickets);
        List<UserTicket> result = logic.invoke("12");
        assertEquals(result, userTickets);
    }
}
