package com.kasun.airline.service.account;

import com.kasun.airline.common.dto.Price;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.dto.account.AccountResponse;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.dto.account.MoneyTransferRequest;
import com.kasun.airline.model.account.Currency;
import com.kasun.airline.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountHibernateDao;

    @Autowired
    UserService userService;

    @Transactional
    @Override
    public BankAccount createAccount(BankAccount bankAccount) {

        validateAccount(bankAccount);
        BankAccount account = accountHibernateDao.createAccount(bankAccount);
        makeBankAccountResponse(account);
        return account;
    }

    private void makeBankAccountResponse(BankAccount account) {

        account.getUser().setUserBankAccounts(null);
    }

    private void validateAccount(BankAccount bankAccount) {
        if (bankAccount.getAvailableAmount() == null) {
            bankAccount.setAvailableAmount(0D);
        }
    }

    @Transactional
    @Override
    public void deleteAccount(String accountNumber) {

        BankAccount bankAccount = accountHibernateDao.loadAccountByAccountNumber(accountNumber);
        accountHibernateDao.deleteAccount(bankAccount);
    }

    @Transactional
    @Override
    public BankAccount deposit(DepositRequest depositRequest) {

        BankAccount account = accountHibernateDao.loadAccountByAccountNumber(depositRequest.getAccountId());
        Double newBalance = account.getAvailableAmount() + depositRequest.getPrice().getPrice();
        account.setAvailableAmount(newBalance);
        accountHibernateDao.updateAccount(account);
        return account;
    }


    @Transactional
    @Override
    public BankAccount withdraw(DepositRequest depositRequest) {
        return null;
    }

    @Transactional
    @Override
    public Boolean transferMoney(MoneyTransferRequest request) {

        BankAccount bankAccount = accountHibernateDao.loadAccountByAccountNumber(request.getAccountNumber());
        Double newAmount = 0d;

        if (request.getTransferType().equals(MoneyTransferRequest.TransferType.DEPOSIT)) {
            newAmount = bankAccount.getAvailableAmount() + request.getTransferAmount();

        } else {
            newAmount = bankAccount.getAvailableAmount() - request.getTransferAmount();
        }

        if (newAmount >= 0) {
            bankAccount.setAvailableAmount(newAmount);
        } else {
            return false;
        }

        accountHibernateDao.updateAccount(bankAccount);
        return true;
    }

    @Override
    @Transactional
    public List<BankAccount> loadAllAccounts(String applicantId) {
        return accountHibernateDao.loadAccountByApplicantId(applicantId);
    }

    @Transactional
    @Override
    public void removeAccount(String accountId) {
        accountHibernateDao.removeAccount(accountId);
    }

    @Override
    public Price currencyExchange(Price amount, Currency toCurrency) {

        Currency fromCurrencyCode = amount.getCurrency();
        double conversionRate = CurrencyConverter.convert(fromCurrencyCode.toString(), toCurrency.toString());
        double convertedAmount = conversionRate * amount.getPrice();
        return buildConvertedPrice(toCurrency, convertedAmount);
    }

    private Price buildConvertedPrice(Currency toCurrency, double convertedAmount) {
        Price convertedPrice = new Price();
        convertedPrice.setPrice(convertedAmount);
        convertedPrice.setCurrency(toCurrency);
        return convertedPrice;
    }

    private AccountResponse getAccountResponse(BankAccount acc) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(acc.getAccountId().toString());
        Price price = getPrice(acc);
        accountResponse.setAvailableAmount(price);
        return accountResponse;
    }

    private Price getPrice(BankAccount acc) {
        Price price = new Price();
        price.setCurrency(acc.getCurrency());
        price.setPrice(acc.getAvailableAmount());
        return price;
    }
}
