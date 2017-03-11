package com.kasun.airline.adapter.airline;


import com.kasun.airline.common.dto.Price;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Route;
import org.mockito.InjectMocks;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class AirlineOfferAdapterUnitTest {

    @InjectMocks
    AirlineOfferAdapter adapter = new AirlineOfferAdapter();

    @Test
    public void adaptFromDtoNullTest() {
        assertNull(adapter.adaptFromDto(null));
    }

    @Test
    public void adaptFromDtoTest() {

        AirlineOffer airlineOffer = new AirlineOffer();
        airlineOffer.setRoute(new Route());
        airlineOffer.setPrice(new Price());
        assertNotNull(adapter.adaptFromDto(airlineOffer));
    }

    @Test
    public void adaptFromModelListTest() {

        List<AirlineOfferModel> airlineOfferModels = new ArrayList<>();
        AirlineOfferModel airlineOfferModel = new AirlineOfferModel();
        airlineOfferModels.add(airlineOfferModel);
        List<AirlineOffer> airlineOffers = adapter.adaptFromModelList(airlineOfferModels);
        assertNotNull(airlineOffers);
        assertEquals(airlineOffers.size(), 1);
    }
}
