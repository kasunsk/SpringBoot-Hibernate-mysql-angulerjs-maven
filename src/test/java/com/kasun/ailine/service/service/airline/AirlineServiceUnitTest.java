package com.kasun.ailine.service.service.airline;

import com.kasun.ailine.service.adapter.airline.AirlineOfferAdapter;
import com.kasun.ailine.service.dao.account.AccountDao;
import com.kasun.ailine.service.dao.airline.AirlineDao;
import com.kasun.ailine.service.dto.airline.TicketBuyingRequest;
import com.kasun.ailine.service.model.airline.AirlineOfferModel;
import com.kasun.ailine.service.service.user.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

/**
 * Created by kasun on 2/6/17.
 */
public class AirlineServiceUnitTest {

    @InjectMocks
    AirlineServiceImpl airlineService = new AirlineServiceImpl();

    @Mock
    AirlineDao airlineDao;

    @Mock
    AccountDao accountDao;

    @Mock
    AirlineOfferAdapter offerAdapter;

    @Mock
    UserService userService;

    @BeforeMethod(alwaysRun=true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RuntimeException.class)
    public void validateAirlineOfferInventoryAvailabilityTest(){

        AirlineOfferModel airlineOffer = new AirlineOfferModel();
        airlineOffer.setAvailbaleInventory(2);

        TicketBuyingRequest request = new TicketBuyingRequest();
        request.setTicketAmount(5);

        airlineService.validateAirlineOfferInventoryAvailability(airlineOffer, request);
    }


}
