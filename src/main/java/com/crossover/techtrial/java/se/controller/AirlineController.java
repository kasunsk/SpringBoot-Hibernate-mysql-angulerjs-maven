package com.crossover.techtrial.java.se.controller;

import com.crossover.techtrial.java.se.dto.AirlineOffer;
import com.crossover.techtrial.java.se.dto.OfferRequest;
import com.crossover.techtrial.java.se.dto.TicketBuyingRequest;
import com.crossover.techtrial.java.se.service.airline.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/{applicantId}/gammaairlines/offers", method = RequestMethod.GET)
    public String retrieveAvailableOffers(@PathVariable("applicantId") String applicantId,
                                                      ModelMap model) {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.setApplicantId(applicantId);
        List<AirlineOffer> offers = airlineService.retrieveAvailableAirlineOffers(offerRequest);
        model.addAttribute("offers", offers);
        model.addAttribute("edit", false);
        return "offerlist";
    }

    @RequestMapping(value = "/gammaairlines/offers/new", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Boolean createOffers(@RequestBody AirlineOffer airlineOffer) {

        airlineService.createAirlineOffer(airlineOffer);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/{applicantId}/gammaairlines/tickets", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Boolean retrieveApplicantTickets(@RequestBody AirlineOffer airlineOffer) {

        airlineService.createAirlineOffer(airlineOffer);
        return Boolean.TRUE;
    }


    @RequestMapping(value = "/gammaairlines/offers/buy", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Boolean buyAOffer(@RequestBody TicketBuyingRequest buyingRequest) {

        airlineService.buyAirlineTicket(buyingRequest);
        return Boolean.TRUE;
    }

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

}
