package com.kasun.airline.service.airline;

import com.kasun.airline.adapter.airline.AirlineOfferAdapter;
import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.common.service.RequestAssembler;
import com.kasun.airline.dao.airline.AirlineHibernateDao;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.dto.airline.OfferRequest;
import com.kasun.airline.logic.UserAllTicketsLogic;
import com.kasun.airline.logic.airline.AirlineOfferCreateLogic;
import com.kasun.airline.logic.airline.AirlineOfferRemoveLogic;
import com.kasun.airline.logic.airline.ApplicantTicketsRetrieveLogic;
import com.kasun.airline.logic.airline.AvailableAirlineOfferRetrieveLogic;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.model.account.Currency;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Airport;
import com.kasun.airline.model.airline.Route;
import com.kasun.airline.model.user.UserTicket;
import com.kasun.airline.service.account.AccountService;
import com.kasun.airline.service.user.UserService;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.dto.airline.TicketBuyingRequest;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

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
    private AccountService accountService;

    @Autowired
    private AirlineOfferCreateLogic airlineOfferCreateLogic;

    @Autowired
    private UserAllTicketsLogic userAllTicketsLogic;

    @Autowired
    private AvailableAirlineOfferRetrieveLogic availableAirlineOfferRetrieveLogic;

    @Autowired
    private AirlineOfferRemoveLogic airlineOfferRemoveLogic;

    @Autowired
    private ApplicantTicketsRetrieveLogic applicantTicketsRetrieveLogic;

    @Override
    public ServiceResponse<Void> createAirlineOffer(ServiceRequest<AirlineOffer> airlineOffer) {

       return RequestAssembler.assemble(airlineOfferCreateLogic, airlineOffer);
    }


    @Transactional
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


    @Transactional
    public UserTicket buyAirlineTicket(TicketBuyingRequest request, String applicantId) {

        BankAccount applicantBankAccount = accountDao.loadAccountById(Long.parseLong(request.getAccountId()));
        Double availableAmount = applicantBankAccount.getAvailableAmount();
        AirlineOfferModel airlineOffer = loadOfferByRout(request.getAirlineRout());
        validateAirlineOfferInventoryAvailability(airlineOffer, request);
        Price offerPrice = getConvertedOfferPrice(airlineOffer, applicantBankAccount.getCurrency());
        Price price = calculatePaymentAmount(availableAmount, offerPrice, request.getTicketAmount());
        processPayment(price, applicantBankAccount);

        UserTicket userTicket = getUserTicket(applicantId, airlineOffer, request);
        accountDao.saveUserTicket(userTicket);
        updateInventory(airlineOffer, request);
        return userTicket;
    }

    private Price getConvertedOfferPrice(AirlineOfferModel airlineOffer, Currency accountCurrency) {

        Price price = new Price();
        price.setPrice(airlineOffer.getPrice());
        price.setCurrency(airlineOffer.getCurrency());

        if (!airlineOffer.getCurrency().equals(accountCurrency)) {
            price = accountService.currencyExchange(price, accountCurrency);
        }
        return price;
    }

    @Transactional
    @Override
    public List<Airport> allAirports() {
        return airlineDao.loadAllAirports();
    }

    @Override
    public ServiceResponse<List<UserTicket>> usersTickets(ServiceRequest<String> applicantId) {

        return RequestAssembler.assemble(userAllTicketsLogic, applicantId);
    }

    private UserTicket getUserTicket(String applicantId, AirlineOfferModel airlineOffer, TicketBuyingRequest request) {
        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(Long.parseLong(applicantId));
        userTicket.setOfferId(airlineOffer.getOfferId());
        userTicket.setStatus(UserTicketStatus.BOUGHT);
        userTicket.setDestination(airlineOffer.getDestination());
        userTicket.setOrigin(airlineOffer.getOrigin());
        userTicket.setPrice(airlineOffer.getPrice());
        userTicket.setCurrency(airlineOffer.getCurrency());
        userTicket.setTicketsAmount(request.getTicketAmount());
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

        BigDecimal payableAmount = BigDecimal.valueOf(price.getPrice()).multiply(new BigDecimal(ticketAmount));

        if (BigDecimal.valueOf(availableAmount).compareTo(payableAmount) == -1) {
            throw new ServiceRuntimeException(ErrorCode.NOT_ENOUGH_CREDIT, "Credit not enough in given account");
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

        ValidationUtil.validate(airlineOffer, "Airline offer can not be null");
        ValidationUtil.validate(airlineOffer.getPrice(), "Price can not be null");
        ValidationUtil.validate(airlineOffer.getRoute(), "Route can not be null");
        Route airlineOfferRoute = airlineOffer.getRoute();

        if (airlineOfferRoute.getFrom().equals(airlineOfferRoute.getTo())) {
            throw new ServiceRuntimeException(ErrorCode.INVALID_OFFER_ROUT, "Invalid route");
        }
        validateRoutAlreadyAvailable(airlineOffer);
    }

    private void validateRoutAlreadyAvailable(AirlineOffer airlineOffer) {

        Route route = airlineOffer.getRoute();
        AirlineOfferModel airlineOfferModel = loadOfferByRout(route);

        if (airlineOfferModel != null) {
            throw new ServiceRuntimeException(ErrorCode.ROUTE_ALREADY_EXIST, "Offer rout already exist, Please remove current offer to add new");
        }
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
