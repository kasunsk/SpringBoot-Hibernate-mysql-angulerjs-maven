package com.kasun.airline.service.airline;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.RequestAssembler;
import com.kasun.airline.dto.airline.OfferRequest;
import com.kasun.airline.dto.airline.TicketBuy;
import com.kasun.airline.logic.airline.*;
import com.kasun.airline.model.airline.Airport;
import com.kasun.airline.model.user.UserTicket;
import com.kasun.airline.dto.airline.AirlineOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("airlineService")
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineOfferCreateLogic airlineOfferCreateLogic;

    @Autowired
    private AvailableAirlineOfferRetrieveLogic availableAirlineOfferRetrieveLogic;

    @Autowired
    private AirlineOfferRemoveLogic airlineOfferRemoveLogic;

    @Autowired
    private ApplicantTicketsRetrieveLogic applicantTicketsRetrieveLogic;

    @Autowired
    private AirlineTicketBuyingLogic airlineTicketBuyingLogic;

    @Autowired
    private AllAirportsLoadingLogic allAirportsLoadingLogic;

    @Autowired
    private UserTicketEmailSendingLogic userTicketEmailSendingLogic;

    @Override
    public ServiceResponse<Void> createAirlineOffer(ServiceRequest<AirlineOffer> airlineOffer) {

       return RequestAssembler.assemble(airlineOfferCreateLogic, airlineOffer);
    }


    @Override
    public ServiceResponse<Void> removeAirlineOffer(ServiceRequest<String> airlineOfferId) {

        return RequestAssembler.assemble(airlineOfferRemoveLogic, airlineOfferId);
    }


    @Override
    public ServiceResponse<List<AirlineOffer>> retrieveAvailableAirlineOffers(ServiceRequest<OfferRequest> offerRequest) {

        return RequestAssembler.assemble(availableAirlineOfferRetrieveLogic, offerRequest);
    }

    @Override
    public ServiceResponse<List<UserTicket>> retrieveApplicantTickets(ServiceRequest<String> applicantId) {

        return RequestAssembler.assemble(applicantTicketsRetrieveLogic, applicantId);
    }


    @Override
    public ServiceResponse<UserTicket> buyAirlineTicket(ServiceRequest<TicketBuy> request) {

        return RequestAssembler.assemble(airlineTicketBuyingLogic, request);
    }

    @Override
    public ServiceResponse<List<Airport>> loadAllAirports(ServiceRequest<Void> voidServiceRequest) {

        return RequestAssembler.assemble(allAirportsLoadingLogic, voidServiceRequest);
    }

    @Override
    public ServiceResponse<Boolean> sendUserTicketEmail(ServiceRequest<String> userTicketId) {

        return RequestAssembler.assemble(userTicketEmailSendingLogic, userTicketId);
    }

}
