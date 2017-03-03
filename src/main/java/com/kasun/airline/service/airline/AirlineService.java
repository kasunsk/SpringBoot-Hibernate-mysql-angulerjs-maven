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
 * This API provide all the service method for airline service
 */
public interface AirlineService {

    /**
     *
     * @param airlineOffer
     * @return void
     */
    ServiceResponse<Void> createAirlineOffer(ServiceRequest<AirlineOffer> airlineOffer);

    /**
     *
     * @param airlineOfferId
     * @return void
     */
    ServiceResponse<Void> removeAirlineOffer(ServiceRequest<String> airlineOfferId);

    /**
     *
     * @param offerRequest
     * @return List of AirlineOffer
     */
    ServiceResponse<List<AirlineOffer>> retrieveAvailableAirlineOffers(ServiceRequest<OfferRequest> offerRequest);

    /**
     *
     * @param applicantId
     * @return List of UserTicket
     */
    ServiceResponse<List<UserTicket>> retrieveApplicantTickets(ServiceRequest<String> applicantId);

    /**
     *
     * @param ticketBuyRequest
     * @return UserTicket
     */
    ServiceResponse<UserTicket> buyAirlineTicket(ServiceRequest<TicketBuy> ticketBuyRequest);

    /**
     *
     * @param voidServiceRequest
     * @return List of all Airport
     */
    ServiceResponse<List<Airport>> loadAllAirports(ServiceRequest<Void> voidServiceRequest);
}
