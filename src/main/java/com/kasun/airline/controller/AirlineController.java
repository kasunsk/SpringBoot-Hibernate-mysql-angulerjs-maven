package com.kasun.airline.controller;

import com.kasun.airline.common.dto.EmailRequest;
import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.dto.airline.OfferRequest;
import com.kasun.airline.dto.airline.TicketBuyingRequest;
import com.kasun.airline.model.airline.Airport;
import com.kasun.airline.model.user.UserTicket;
import com.kasun.airline.service.airline.AirlineService;
import com.kasun.airline.service.email.EmailService;
import com.kasun.airline.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
@Controller
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/{applicantId}/gammaairlines/offers", method = RequestMethod.GET)
    @ResponseBody
    public  List<AirlineOffer> retrieveAvailableOffers(@PathVariable("applicantId") String applicantId,
                                                      ModelMap model) {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setApplicantId(applicantId);
        ServiceResponse<List<AirlineOffer>> response = airlineService.retrieveAvailableAirlineOffers(new ServiceRequest<>(offerRequest));
        return response.getPayload();
    }

    @RequestMapping(value = "/gammaairlines/offers/save", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Boolean createOffers(@RequestBody AirlineOffer airlineOffer) {

        airlineService.createAirlineOffer(airlineOffer);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/{applicantId}/gammaairlines/tickets", method = RequestMethod.GET)
    @ResponseBody
    public List<UserTicket> retrieveApplicantTickets(@PathVariable("applicantId") String applicantId) {

        return airlineService.retrieveApplicantTickets(applicantId);
    }


    @RequestMapping(value = "/{applicantId}/gammaairlines/offers/buy", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public UserTicket buyAOffer(@PathVariable("applicantId") String applicantId,@RequestBody TicketBuyingRequest buyingRequest) {

        validateUser(applicantId);
        return airlineService.buyAirlineTicket(buyingRequest, applicantId);
    }

    @RequestMapping(value = "/gammaairlines/country/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Airport> loadAllCountries() {

        return airlineService.allAirports();
    }

    @RequestMapping(value = "/gammaairlines/offer/remove/{offerId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean loadAllCountries(@PathVariable("offerId") String offerId) {

         airlineService.removeAirlineOffer(offerId);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/gammaairlines/email/send", method = RequestMethod.POST)
    @ResponseBody
    public Boolean sendEmail(@RequestBody EmailRequest emailRequest) {

        emailService.sendEmail(emailRequest);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/{applicantId}/gammaairlines/tickets/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserTicket> loadUsersTickers(@PathVariable("applicantId") String applicantId,@PathVariable("userId") String userId) {

        validateUser(applicantId);
        return airlineService.retrieveApplicantTickets(userId);
    }


    private void validateUser(String applicantId) {

        userService.authenticateUser(applicantId);
    }

}
