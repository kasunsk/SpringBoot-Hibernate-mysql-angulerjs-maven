package com.crossover.techtrial.java.se.dao.account;

import com.crossover.techtrial.java.se.dao.AbstractDao;
import com.crossover.techtrial.java.se.model.account.Account;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

/**
 * Created by kasun on 2/4/17.
 */
@Repository
public class AccountHibernateDao extends AbstractDao<Long,Account> implements AccountDao {

    public Account loadAccountByApplicantId(String applicantId) {
        return null;
    }

    public Account loadAccountById(Long accountId) {
        return null;
    }

    public Account loadAccountByAccountNumber(String accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account = (Account)getSession().createCriteria(Account.class).add(Example.create(account)).uniqueResult();
        return account;
    }

    public void updateAccount(Account applicantAccount) {

    }

    public void createAccount(Account account) {
        getSession().save(account);
    }

    public void deleteAccount(Account account) {
        getSession().delete(account);
    }

}
