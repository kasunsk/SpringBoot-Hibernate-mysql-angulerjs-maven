package com.kasun.airline.logic.airline;


import com.kasun.airline.adapter.airline.AirlineOfferAdapter;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.dto.airline.OfferRequest;
import com.kasun.airline.model.airline.AirlineOfferModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class AvailableAirlineOfferRetrieveLogicUnitTest {

    @InjectMocks
    private AvailableAirlineOfferRetrieveLogic logic = new AvailableAirlineOfferRetrieveLogic();


    @Mock
    private AirlineDao airlineDao;

    @Mock
    private AirlineOfferAdapter offerAdapter;

    @Mock
    private AirlineOfferLogicHelper logicHelper;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void validateTest() {
        logic.invoke(null);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void invalidApplicantTest() {
        logic.invoke(new OfferRequest());
    }

    @Test
    public void invokeTest() {

        List<AirlineOfferModel> airlineOfferModels = new ArrayList<>();
        when(airlineDao.loadAirlineOffers(AirlineOffer.AirlineOfferStatus.AVAILABLE)).thenReturn(airlineOfferModels);
        List<AirlineOffer> airlineOffers = new ArrayList<>();
        when(offerAdapter.adaptFromModelList(airlineOfferModels)).thenReturn(airlineOffers);

        OfferRequest offerRequest = getOfferRequest();
        List<AirlineOffer> result = logic.invoke(offerRequest);
        assertEquals(result, airlineOffers);
    }

    private OfferRequest getOfferRequest() {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setApplicantId("12");
        offerRequest.setStatus(AirlineOffer.AirlineOfferStatus.AVAILABLE);
        return offerRequest;
    }
}
