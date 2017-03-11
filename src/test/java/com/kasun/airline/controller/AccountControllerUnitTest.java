package com.kasun.airline.controller;


import com.kasun.airline.common.dto.CurrencyExchangeRequest;
import com.kasun.airline.common.dto.Price;
import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.dto.account.AccountRequest;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.model.account.Currency;
import com.kasun.airline.model.user.User;
import com.kasun.airline.service.account.AccountService;
import com.kasun.airline.service.user.UserService;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class AccountControllerUnitTest {

    @InjectMocks
    AccountController controller = new AccountController();

    @Mock
    AccountService accountService;

    @Mock
    UserService userService;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createAccountTest() {

        ServiceResponse<BankAccount> accountResponse = new ServiceResponse<>();
        BankAccount bankAccount = new BankAccount();
        accountResponse.setPayload(bankAccount);
        when(accountService.createAccount(org.mockito.Matchers.<ServiceRequest>any())).thenReturn(accountResponse);
        ServiceResponse<User> userResponse = new ServiceResponse<>();
        userResponse.setPayload(new User());
        when(userService.loadUserById(org.mockito.Matchers.<ServiceRequest>any())).thenReturn(userResponse);
        AccountRequest request = new AccountRequest();
        request.setCurrency(Currency.AUD);
        assertEquals(controller.createAccount("23", request), bankAccount);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void depositsTest() {

        ServiceResponse<BankAccount> accountResponse = new ServiceResponse<>();
        BankAccount bankAccount = new BankAccount();
        accountResponse.setPayload(bankAccount);
        when(accountService.deposit(org.mockito.Matchers.<ServiceRequest>any())).thenReturn(accountResponse);
        DepositRequest request = new DepositRequest();
        request.setPrice(new Price());
        assertEquals(controller.deposits("23", request), bankAccount);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void withdrawTest() {

        ServiceResponse<BankAccount> accountResponse = new ServiceResponse<>();
        BankAccount bankAccount = new BankAccount();
        accountResponse.setPayload(bankAccount);
        when(accountService.withdraw(Matchers.<ServiceRequest>any())).thenReturn(accountResponse);
        DepositRequest request = new DepositRequest();
        request.setPrice(new Price());
        assertEquals(controller.withdraw("23", request), bankAccount);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void viewAllAccountsTest() {

        ServiceResponse<List<BankAccount>> accountResponse = new ServiceResponse<>();
        List<BankAccount> accounts = new ArrayList<>();
        BankAccount bankAccount = new BankAccount();
        accounts.add(bankAccount);
        accountResponse.setPayload(accounts);
        when(accountService.loadAllAccounts(Matchers.<ServiceRequest>any())).thenReturn(accountResponse);
        assertEquals(controller.viewAllAccounts("23"), accounts);
    }

    @Test
    public void removeAccountTest() {
        controller.removeAccount("2", "4");
        verify(accountService, times(1)).removeAccount(any());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void moneyExchangeTest() {

        ServiceResponse<Price> accountResponse = new ServiceResponse<>();
        Price price = new Price();
        accountResponse.setPayload(price);
        when(accountService.exchangeCurrency(Matchers.<ServiceRequest>any())).thenReturn(accountResponse);
        assertEquals(controller.moneyExchange("23", new CurrencyExchangeRequest()), price);
    }
}
