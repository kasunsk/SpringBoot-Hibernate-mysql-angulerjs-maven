package com.crossover.techtrial.java.se.service.airline;

import com.crossover.techtrial.java.se.dto.AirlineOffer;
import com.crossover.techtrial.java.se.dto.AirlineTicket;
import com.crossover.techtrial.java.se.dto.OfferRequest;
import com.crossover.techtrial.java.se.dto.TicketBuyingRequest;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AirlineService {

    void createAirlineOffer(AirlineOffer airlineOffer);

    List<AirlineOffer> retrieveAvailableAirlineOffers(OfferRequest offerRequest);

    List<AirlineTicket> retrieveApplicantTicket(String applicantId);

    void buyAirlineTicket(TicketBuyingRequest request);

}
