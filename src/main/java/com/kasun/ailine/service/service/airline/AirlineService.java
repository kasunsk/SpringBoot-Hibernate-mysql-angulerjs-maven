package com.kasun.ailine.service.service.airline;

import com.kasun.ailine.service.common.dto.ServiceRequest;
import com.kasun.ailine.service.common.dto.ServiceResponse;
import com.kasun.ailine.service.dto.airline.AirlineOffer;
import com.kasun.ailine.service.dto.airline.OfferRequest;
import com.kasun.ailine.service.dto.airline.TicketBuyingRequest;
import com.kasun.ailine.service.model.user.UserTicket;

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
