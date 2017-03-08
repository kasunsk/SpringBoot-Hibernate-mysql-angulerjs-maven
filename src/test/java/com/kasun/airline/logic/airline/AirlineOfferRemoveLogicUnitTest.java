package com.kasun.airline.logic.airline;


import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.airline.AirlineDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class AirlineOfferRemoveLogicUnitTest {

    @InjectMocks
    AirlineOfferRemoveLogic logic = new AirlineOfferRemoveLogic();

    @Mock
    private AirlineDao airlineDao;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void offerIdInvalidTest() {
        logic.invoke(null);
    }

    @Test
    public void offerRemoveTest() {
        logic.invoke("2");
        verify(airlineDao, times(1)).remove("2");
    }


}
