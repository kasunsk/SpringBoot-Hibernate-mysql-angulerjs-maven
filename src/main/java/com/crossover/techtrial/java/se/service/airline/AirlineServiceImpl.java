package com.crossover.techtrial.java.se.service.airline;

import com.crossover.techtrial.java.se.adapter.airline.AirlineOfferAdapter;
import com.crossover.techtrial.java.se.common.dto.Price;
import com.crossover.techtrial.java.se.common.dto.ServiceRequest;
import com.crossover.techtrial.java.se.common.dto.ServiceResponse;
import com.crossover.techtrial.java.se.common.dto.UserTicketStatus;
import com.crossover.techtrial.java.se.common.execption.ErrorCode;
import com.crossover.techtrial.java.se.common.execption.ServiceRuntimeException;
import com.crossover.techtrial.java.se.common.service.RequestAssembler;
import com.crossover.techtrial.java.se.dao.airline.AirlineHibernateDao;
import com.crossover.techtrial.java.se.dao.account.AccountDao;
import com.crossover.techtrial.java.se.dao.airline.AirlineDao;
import com.crossover.techtrial.java.se.dto.airline.*;
import com.crossover.techtrial.java.se.logic.UserAllTicketsLogic;
import com.crossover.techtrial.java.se.model.account.BankAccount;
import com.crossover.techtrial.java.se.model.airline.AirlineOfferModel;
import com.crossover.techtrial.java.se.model.airline.Route;
import com.crossover.techtrial.java.se.model.user.UserTicket;
import com.crossover.techtrial.java.se.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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

    @Autowired
    private UserAllTicketsLogic userAllTicketsLogic;

    @Transactional
    public void createAirlineOffer(AirlineOffer airlineOffer) {

        validateAirlineOffer(airlineOffer);
        AirlineOfferModel offerModel = offerAdapter.adaptFromDto(airlineOffer);
        airlineDao.saveAirlineOffer(offerModel);
    }

    @Transactional
    @Override
    public void removeAirlineOffer(String airlineOfferId) {
        airlineDao.remove(airlineOfferId);
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
    public List<UserTicket> retrieveApplicantTickets(String applicantId) {

        List<UserTicket> userTickets = airlineDao.loadApplicantAirlineOffers(Long.parseLong(applicantId));
        return userTickets;
    }

    @Transactional
    public void buyAirlineTicket(TicketBuyingRequest request, String applicantId) {

        BankAccount applicantBankAccount = accountDao.loadAccountById(Long.parseLong(request.getAccountId()));
        Double availableAmount = applicantBankAccount.getAvailableAmount();
        AirlineOfferModel airlineOffer = loadOfferByRout(request.getAirlineRout());
        validateAirlineOfferInventoryAvailability(airlineOffer, request);
        Price offerPrice = getOfferPrice(airlineOffer);
        Price price = calculatePaymentAmount(availableAmount, offerPrice, request.getTicketAmount());
        processPayment(price, applicantBankAccount);

        UserTicket userTicket = getUserTicket(applicantId, airlineOffer);
        accountDao.saveUserTicket(userTicket);
        updateInventory(airlineOffer, request);

    }

    private Price getOfferPrice(AirlineOfferModel airlineOffer) {

        Price price = new Price();
        price.setPrice(airlineOffer.getPrice());
        price.setCurrency(airlineOffer.getCurrency());
        return price;
    }

    @Transactional
    @Override
    public List<String> allAirports() {
        return airlineDao.loadAllAirports();
    }

    @Override
    public ServiceResponse<List<UserTicket>> usersTickets(ServiceRequest<String> applicantId) {

        return RequestAssembler.assemble(userAllTicketsLogic, applicantId);
    }

    private UserTicket getUserTicket(String applicantId, AirlineOfferModel airlineOffer) {
        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(Long.parseLong(applicantId));
        userTicket.setOfferId(airlineOffer.getOfferId());
        userTicket.setStatus(UserTicketStatus.BOUGHT);
        userTicket.setDestination(airlineOffer.getDestination());
        userTicket.setOrigin(airlineOffer.getOrigin());
        userTicket.setPrice(airlineOffer.getPrice());
        userTicket.setCurrency(airlineOffer.getCurrency());
        return userTicket;
    }

    private void updateInventory(AirlineOfferModel airlineOffer, TicketBuyingRequest request) {

        Integer newAvailableInventory = airlineOffer.getAvailbaleInventory() - request.getTicketAmount();
        airlineOffer.setAvailbaleInventory(newAvailableInventory);
        airlineDao.updateAirlineOffer(airlineOffer);

    }

    private void processPayment(Price price, BankAccount applicantBankAccount) {

        Double newAccountBalance = applicantBankAccount.getAvailableAmount() - price.getPrice();
        applicantBankAccount.setAvailableAmount(newAccountBalance);
        accountDao.updateAccount(applicantBankAccount);
    }

    protected void validateAirlineOfferInventoryAvailability(AirlineOfferModel airlineOffer, TicketBuyingRequest request) {

        Integer availableInventory = airlineOffer.getAvailbaleInventory();

        if (availableInventory < request.getTicketAmount()) {
            throw new ServiceRuntimeException(ErrorCode.NO_ENOUGH_INV, "No enough inventory for Airline Offer ");
        }
    }

    private Price calculatePaymentAmount(Double availableAmount, Price price, Integer ticketAmount) {

        BigDecimal payableAmount = BigDecimal.valueOf(price.getPrice()).add(new BigDecimal(ticketAmount));

        if (BigDecimal.valueOf(availableAmount).compareTo(payableAmount) == -1) {
            throw new RuntimeException();
        }

        Price amount = new Price();
        amount.setCurrency(price.getCurrency());
        amount.setPrice(payableAmount.doubleValue());
        return amount;
    }

    private AirlineOfferModel loadOfferByRout(Route airlineRout) {
        return airlineDao.loadOfferByRoute(airlineRout.getFrom(), airlineRout.getTo());
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
