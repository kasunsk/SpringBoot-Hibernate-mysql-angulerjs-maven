package com.crossover.techtrial.java.se.service.airline;

import com.crossover.techtrial.java.se.common.dto.ServiceRequest;
import com.crossover.techtrial.java.se.common.dto.ServiceResponse;
import com.crossover.techtrial.java.se.dto.airline.AirlineOffer;
import com.crossover.techtrial.java.se.dto.airline.AirlineTicket;
import com.crossover.techtrial.java.se.dto.airline.OfferRequest;
import com.crossover.techtrial.java.se.dto.airline.TicketBuyingRequest;
import com.crossover.techtrial.java.se.model.user.UserTicket;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AirlineService {

    void createAirlineOffer(AirlineOffer airlineOffer);

    void removeAirlineOffer(String airlineOfferId);

    List<AirlineOffer> retrieveAvailableAirlineOffers(OfferRequest offerRequest);

    List<UserTicket> retrieveApplicantTickets(String applicantId);

    void buyAirlineTicket(TicketBuyingRequest request, String applicantId);

    List<String> allAirports();

    ServiceResponse<List<UserTicket>> usersTickets(ServiceRequest<String> applicantId);

}
