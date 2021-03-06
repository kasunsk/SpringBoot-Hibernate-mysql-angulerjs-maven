package com.kasun.airline.controller;

import com.kasun.airline.common.dto.CurrencyExchangeRequest;
import com.kasun.airline.common.dto.Price;
import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.dto.account.AccountRequest;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.model.account.Currency;
import com.kasun.airline.model.user.User;
import com.kasun.airline.service.account.AccountService;
import com.kasun.airline.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * This class provide http rest interface for Account Services
 */

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{applicantId}/paypallets/account", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BankAccount createAccount(@PathVariable("applicantId") String applicantId, @RequestBody AccountRequest accountRequest) {

        BankAccount bankAccount = buildAccountCreateRequest(applicantId, accountRequest);
        return accountService.createAccount(new ServiceRequest<>(bankAccount)).getPayload();
    }

    @RequestMapping(value = "/{applicantId}/paypallets/account/deposit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BankAccount deposits(@PathVariable("applicantId") String applicantId, @RequestBody DepositRequest depositRequest) {

        validateUser(applicantId);
        return accountService.deposit(new ServiceRequest<>(depositRequest)).getPayload();
    }

    @RequestMapping(value = "/{applicantId}/paypallets/account/withdraw", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BankAccount withdraw(@PathVariable("applicantId") String applicantId, @RequestBody DepositRequest depositRequest) {

        validateUser(applicantId);
        return accountService.withdraw(new ServiceRequest<>(depositRequest)).getPayload();
    }

    @RequestMapping(value = "/{applicantId}/paypallets/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<BankAccount> viewAllAccounts(@PathVariable("applicantId") String applicantId) {

        return accountService.loadAllAccounts(new ServiceRequest<>(applicantId)).getPayload();
    }

    @RequestMapping(value = "/{applicantId}/paypallets/account/remove/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean removeAccount(@PathVariable("applicantId") String applicantId, @PathVariable("accountId") String accountId) {

        accountService.removeAccount(new ServiceRequest<>(accountId));
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/{applicantId}/moneyexchange/exchange", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Price moneyExchange(@PathVariable("applicantId") String applicantId, @RequestBody CurrencyExchangeRequest exchangeRequest) {

        validateUser(applicantId);
        return accountService.exchangeCurrency(new ServiceRequest<>(exchangeRequest)).getPayload();
    }

    @RequestMapping(value = "/account/availableCurrency", method = RequestMethod.GET)
    @ResponseBody
    public Set<Currency> availableCurrency() {
        return EnumSet.allOf(Currency.class);
    }

    private void validateUser(String applicantId) {

        userService.authenticateUser(new ServiceRequest<>(applicantId));
    }

    private BankAccount buildAccountCreateRequest(String applicantId, AccountRequest accountRequest) {

        User user = userService.loadUserById(new ServiceRequest<>(applicantId)).getPayload();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setUser(user);
        bankAccount.setCurrency(accountRequest.getCurrency());
        return bankAccount;
    }


}
