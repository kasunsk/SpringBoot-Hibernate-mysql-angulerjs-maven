package com.kasun.airline.service.account;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.RequestAssembler;
import com.kasun.airline.dao.account.AccountDao;
import com.kasun.airline.dto.account.DepositRequest;
import com.kasun.airline.logic.account.*;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.dto.account.MoneyTransferRequest;
import com.kasun.airline.model.account.Currency;
import com.kasun.airline.service.user.UserService;
import com.kasun.airline.util.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private AccountCreateLogic accountCreateLogic;

    @Autowired
    private AccountDeleteLogic accountDeleteLogic;

    @Autowired
    private MoneyDepositLogic moneyDepositLogic;

    @Autowired
    private MoneyWithdrawLogic moneyWithdrawLogic;

    @Autowired
    private MoneyTransferLogic moneyTransferLogic;

    @Autowired
    private AllAccountsLoadingLogic allAccountsLoadingLogic;

    @Autowired
    private AccountRemoveLogic accountRemoveLogic;

    @Autowired
    private CurrencyExchangeLogic currencyExchangeLogic;

    @Override
    public ServiceResponse<BankAccount> createAccount(ServiceRequest<BankAccount> bankAccount) {

        return RequestAssembler.assemble(accountCreateLogic, bankAccount);
    }

    @Override
    public ServiceResponse<Void> deleteAccount(ServiceRequest<String> accountNumber) {

        return RequestAssembler.assemble(accountDeleteLogic, accountNumber);
    }

    @Override
    public ServiceResponse<BankAccount> deposit(ServiceRequest<DepositRequest> depositRequest) {

        return RequestAssembler.assemble(moneyDepositLogic, depositRequest);
    }

    @Override
    public ServiceResponse<BankAccount> withdraw(ServiceRequest<DepositRequest> withdrawRequest) {

        return RequestAssembler.assemble(moneyWithdrawLogic, withdrawRequest);
    }

    @Override
    public ServiceResponse<Boolean> transferMoney(ServiceRequest<MoneyTransferRequest> moneyTransferRequest) {

        return RequestAssembler.assemble(moneyTransferLogic, moneyTransferRequest);
    }

    @Override
    public ServiceResponse<List<BankAccount>> loadAllAccounts(ServiceRequest<String> applicantId) {

        return RequestAssembler.assemble(allAccountsLoadingLogic, applicantId);
    }

    @Override
    public ServiceResponse<Void> removeAccount(ServiceRequest<String> accountId) {

        return RequestAssembler.assemble(accountRemoveLogic,accountId);
    }

    @Override
    public ServiceResponse<Price> exchangeCurrency(ServiceRequest<CurrencyExchangeRequest> exchangeRequest) {

        return RequestAssembler.assemble(currencyExchangeLogic,exchangeRequest);
    }
}
