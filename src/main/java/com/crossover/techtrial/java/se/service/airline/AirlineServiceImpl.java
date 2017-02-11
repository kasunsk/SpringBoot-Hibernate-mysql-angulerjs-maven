package com.crossover.techtrial.java.se.service.airline;

import com.crossover.techtrial.java.se.adapter.airline.AirlineOfferAdapter;
import com.crossover.techtrial.java.se.common.dto.Price;
import com.crossover.techtrial.java.se.dao.account.AccountDao;
import com.crossover.techtrial.java.se.dao.airline.AirlineDao;
import com.crossover.techtrial.java.se.dao.airline.AirlineHibernateDao;
import com.crossover.techtrial.java.se.dto.AirlineOffer;
import com.crossover.techtrial.java.se.dto.AirlineTicket;
import com.crossover.techtrial.java.se.dto.OfferRequest;
import com.crossover.techtrial.java.se.dto.TicketBuyingRequest;
import com.crossover.techtrial.java.se.model.account.Account;
import com.crossover.techtrial.java.se.model.airline.AirlineOfferModel;
import com.crossover.techtrial.java.se.model.airline.Route;
import com.crossover.techtrial.java.se.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.crossover.techtrial.java.se.util.ValidationUtil.validate;

/**
 * Created by kasun on 2/4/17.
 */
@Service("airlineService")
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineDao airlineDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AirlineOfferAdapter offerAdapter;

    @Autowired
    private UserService userService;

    @Transactional
    public void createAirlineOffer(AirlineOffer airlineOffer) {

        validateAirlineOffer(airlineOffer);
        AirlineOfferModel offerModel = offerAdapter.adaptFromDto(airlineOffer);
        airlineDao.saveAirlineOffer(offerModel);
    }

    @Transactional
    public List<AirlineOffer> retrieveAvailableAirlineOffers(OfferRequest offerRequest) {

        authenticateApplicant(offerRequest.getApplicantId());
        List<AirlineOfferModel> offerList = airlineDao.loadAirlineOffers(offerRequest.getStatus());
        return offerAdapter.adaptFromModelList(offerList);
    }

    private void authenticateApplicant(String applicantId) {
        userService.authenticateUser(applicantId);
    }

    @Transactional
    public List<AirlineTicket> retrieveApplicantTicket(String applicantId) {

        return null;
    }

    @Transactional
    public void buyAirlineTicket(TicketBuyingRequest request) {

        Account applicantAccount = accountDao.loadAccountById(Long.parseLong(request.getApplicantId()));
        Double availableAmount = applicantAccount.getAmount();
        AirlineOffer airlineOffer = loadOfferByRout(request.getAirlineRout());
        validateAirlineOfferInventoryAvailability(airlineOffer, request);
        Price price = calculatePaymentAmount(availableAmount, airlineOffer.getPrice(), request.getTicketAmount());
        processPayment(price, applicantAccount);
        updateInventory(airlineOffer, request);
    }

    private void updateInventory(AirlineOffer airlineOffer, TicketBuyingRequest request) {

        Integer newAvailableInventory = airlineOffer.getAvailableInventory() - request.getTicketAmount();
        airlineOffer.setAvailableInventory(newAvailableInventory);
        airlineDao.updateAirlineOffer(offerAdapter.adaptFromDto(airlineOffer));

    }

    private void processPayment(Price price, Account applicantAccount) {

        Double newAccountBalance = applicantAccount.getAmount() - price.getPrice();
        applicantAccount.setAmount(newAccountBalance);
        accountDao.updateAccount(applicantAccount);
    }

    protected void validateAirlineOfferInventoryAvailability(AirlineOffer airlineOffer, TicketBuyingRequest request) {

        Integer availableInventory = airlineOffer.getAvailableInventory();

        if (availableInventory < request.getTicketAmount()) {
            throw new RuntimeException("No enough inventory for Airline Offer ");
        }
    }

    private Price calculatePaymentAmount(Double availableAmount, Price price, Integer ticketAmount) {
        return null;
    }

    private AirlineOffer loadOfferByRout(Route airlineRout) {
        return null;
    }

    private void validateAirlineOffer(AirlineOffer airlineOffer) {

        validate(airlineOffer, "Airline offer can not be null");
        validate(airlineOffer.getPrice(), "Price can not be null");
        validate(airlineOffer.getRoute(), "Route can not be null");

    }

    public void setAirlineDao(AirlineHibernateDao airlineDao) {
        this.airlineDao = airlineDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setOfferAdapter(AirlineOfferAdapter offerAdapter) {
        this.offerAdapter = offerAdapter;
    }
}
