package com.kasun.airline.service.airline;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.dto.airline.OfferRequest;
import com.kasun.airline.dto.airline.TicketBuyingRequest;
import com.kasun.airline.model.airline.Airport;
import com.kasun.airline.model.user.UserTicket;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
public interface AirlineService {

    ServiceResponse<Void> createAirlineOffer(ServiceRequest<AirlineOffer> airlineOffer);

    ServiceResponse<Void> removeAirlineOffer(ServiceRequest<String> airlineOfferId);

    ServiceResponse<List<AirlineOffer>> retrieveAvailableAirlineOffers(ServiceRequest<OfferRequest> offerRequest);

    List<UserTicket> retrieveApplicantTickets(String applicantId);

    UserTicket buyAirlineTicket(TicketBuyingRequest request, String applicantId);

    List<Airport> allAirports();

    ServiceResponse<List<UserTicket>> usersTickets(ServiceRequest<String> applicantId);

}
