package com.crossover.techtrial.java.se.service.airline;

import com.crossover.techtrial.java.se.adapter.airline.AirlineOfferAdapter;
import com.crossover.techtrial.java.se.dao.account.AccountDao;
import com.crossover.techtrial.java.se.dao.airline.AirlineDao;
import com.crossover.techtrial.java.se.dto.airline.AirlineOffer;
import com.crossover.techtrial.java.se.dto.airline.TicketBuyingRequest;
import com.crossover.techtrial.java.se.model.airline.AirlineOfferModel;
import com.crossover.techtrial.java.se.service.user.UserService;
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
