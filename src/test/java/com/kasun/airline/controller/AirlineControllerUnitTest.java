package com.kasun.airline.controller;


import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.dto.airline.TicketBuyingRequest;
import com.kasun.airline.dto.email.EmailParam;
import com.kasun.airline.model.airline.Airport;
import com.kasun.airline.model.user.UserTicket;
import com.kasun.airline.service.airline.AirlineService;
import com.kasun.airline.service.email.EmailService;
import com.kasun.airline.service.user.UserService;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AirlineControllerUnitTest {

    @InjectMocks
    AirlineController controller = new AirlineController();

    @Mock
    private AirlineService airlineService;

    @Mock
    private EmailService emailService;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void retrieveAvailableOffersTest() {

        List<AirlineOffer> airlineOffers = new ArrayList<>();
        ServiceResponse<List<AirlineOffer>> response = new ServiceResponse<>();
        response.setPayload(airlineOffers);
        when(airlineService.retrieveAvailableAirlineOffers(Matchers.<ServiceRequest>any())).thenReturn(response);
        assertEquals(airlineOffers, controller.retrieveAvailableOffers("23"));
    }

    @Test
    public void createOffersTest() {
        assertTrue(controller.createOffers(new AirlineOffer()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void buyOfferTest() {

        ServiceResponse<UserTicket> response = new ServiceResponse<>();
        UserTicket userTicket = new UserTicket();
        response.setPayload(userTicket);
        when(airlineService.buyAirlineTicket(Matchers.<ServiceRequest>any())).thenReturn(response);
        TicketBuyingRequest ticketBuyingRequest = new TicketBuyingRequest();
        assertEquals(userTicket, controller.buyOffer("12", ticketBuyingRequest));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void retrieveApplicantTicketsTest() {

        ServiceResponse<List<UserTicket>> response = new ServiceResponse<>();
        List<UserTicket> userTickets = new ArrayList<>();
        response.setPayload(userTickets);
        when(airlineService.retrieveApplicantTickets(Matchers.<ServiceRequest>any())).thenReturn(response);
        assertEquals(userTickets, controller.retrieveApplicantTickets("12"));
    }

    @Test
    public void removeOfferTest() {

        assertTrue(controller.removeOffer("12"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void loadAllAirportsTest() {

        List<Airport> airports = new ArrayList<>();
        ServiceResponse<List<Airport>> response = new ServiceResponse<>();
        response.setPayload(airports);
        when(airlineService.loadAllAirports(Matchers.<ServiceRequest>any())).thenReturn(response);
        controller.loadAllAirports();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void sendUserTicketEmailTest() {

        ServiceResponse<Boolean> response = new ServiceResponse<>();
        response.setPayload(Boolean.TRUE);
        when(airlineService.sendUserTicketEmail(Matchers.<ServiceRequest>any())).thenReturn(response);
        assertTrue(controller.sendUserTicketEmail("2"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void loadUsersTickersTest() {

        ServiceResponse<List<UserTicket>> response = new ServiceResponse<>();
        List<UserTicket> userTickets = new ArrayList<>();
        response.setPayload(userTickets);
        when(airlineService.retrieveApplicantTickets(Matchers.<ServiceRequest>any())).thenReturn(response);
        assertEquals(userTickets, controller.loadUsersTickers("12", "23"));
    }

}
