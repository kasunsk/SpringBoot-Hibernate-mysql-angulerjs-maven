package com.kasun.airline.service.airline;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.dto.airline.OfferRequest;
import com.kasun.airline.dto.airline.TicketBuy;
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

    ServiceResponse<List<UserTicket>> retrieveApplicantTickets(ServiceRequest<String> applicantId);

    ServiceResponse<UserTicket> buyAirlineTicket(ServiceRequest<TicketBuy> request);

    ServiceResponse<List<Airport>> loadAllAirports(ServiceRequest<Void> voidServiceRequest);
}
