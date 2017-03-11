package com.kasun.airline.logic.account;


import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.dto.account.MoneyTransferRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.model.account.Currency;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class MoneyTransferLogicUnitTest {

    @InjectMocks
    MoneyTransferLogic logic = new MoneyTransferLogic();

    @Mock
    AccountDao accountDao;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void requestValidateNullTest() {
        logic.invoke(null);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void requestAccountNumberValidateNullTest() {
        logic.invoke(new MoneyTransferRequest());
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void requestTransferAmountValidateNullTest() {

        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("25");
        logic.invoke(request);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void requestCurrencyValidateNullTest() {

        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("25");
        request.setTransferAmount(200D);
        logic.invoke(request);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void requestTransferTypeValidateNullTest() {

        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("25");
        request.setTransferAmount(200D);
        request.setCurrency(Currency.AUD);
        logic.invoke(request);
    }

    @Test(expectedExceptions = ServiceRuntimeException.class)
    public void accountNotAvailableTest() {

        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("25");
        request.setTransferAmount(200D);
        request.setCurrency(Currency.AUD);
        request.setTransferType(MoneyTransferRequest.TransferType.DEPOSIT);
        when(accountDao.loadAccountById(25L)).thenReturn(null);
        logic.invoke(request);
    }

    @Test
    public void invokeTest() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAvailableAmount(25000D);
        bankAccount.setCurrency(Currency.USD);

        when(accountDao.loadAccountById(25L)).thenReturn(bankAccount);
        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("25");
        request.setTransferAmount(200D);
        request.setCurrency(Currency.AUD);
        request.setTransferType(MoneyTransferRequest.TransferType.DEPOSIT);
        logic.invoke(request);
    }

}
