package com.kasun.ailine.service.service.account;

import com.kasun.ailine.service.dao.account.AccountDao;
import com.kasun.ailine.service.dto.account.MoneyTransferRequest;
import com.kasun.ailine.service.model.account.BankAccount;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by kasun on 2/6/17.
 */
public class BankAccountServiceUnitTest {

    @InjectMocks
    AccountService accountService = new AccountServiceImpl();

    @Mock
    AccountDao accountHibernateDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransferMoneyDepositTest() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAvailableAmount(1000d);
        when(accountHibernateDao.loadAccountByAccountNumber("number1")).thenReturn(bankAccount);
        doNothing().when(accountHibernateDao).updateAccount(any(BankAccount.class));
        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("number1");
        request.setTransferAmount(500d);
        request.setTransferType(MoneyTransferRequest.TransferType.DEPOSIT);
        boolean result = accountService.transferMoney(request);
        assertEquals(new Double(1500), bankAccount.getAvailableAmount());
        assertTrue(result);

    }

    @Test
    public void testTransferMoneyWithdrawTest() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAvailableAmount(1000d);
        when(accountHibernateDao.loadAccountByAccountNumber("number1")).thenReturn(bankAccount);
        doNothing().when(accountHibernateDao).updateAccount(any(BankAccount.class));
        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("number1");
        request.setTransferAmount(500d);
        request.setTransferType(MoneyTransferRequest.TransferType.WITHDRAW);
        boolean result = accountService.transferMoney(request);
        assertEquals(new Double(500), bankAccount.getAvailableAmount());
        assertTrue(result);
    }

    @Test
    public void testTransferMoneyWithdrawFailTest() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAvailableAmount(1000d);
        when(accountHibernateDao.loadAccountByAccountNumber("number1")).thenReturn(bankAccount);
        doNothing().when(accountHibernateDao).updateAccount(any(BankAccount.class));
        MoneyTransferRequest request = new MoneyTransferRequest();
        request.setAccountNumber("number1");
        request.setTransferAmount(1500d);
        request.setTransferType(MoneyTransferRequest.TransferType.WITHDRAW);
        boolean result = accountService.transferMoney(request);
        assertEquals(new Double(1000), bankAccount.getAvailableAmount());
        assertFalse(result);
    }
}
