package com.kasun.airline.dao.account;

import com.kasun.airline.dao.AbstractDao;
import com.kasun.airline.model.account.BankAccount;
import com.kasun.airline.model.user.UserTicket;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountHibernateDao extends AbstractDao<Long, BankAccount> implements AccountDao {

    @SuppressWarnings("uncheked")
    public List<BankAccount> loadAccountByApplicantId(String applicantId) {

        Query query = getSession().createQuery("from BankAccount where USER_ID=:applicantId");
        query.setString("applicantId", applicantId);
        return query.list();
    }

    public BankAccount loadAccountById(Long accountId) {

        Query query = getSession().createQuery("from BankAccount where accountId=:accountId");
        query.setParameter("accountId", accountId);
        return (BankAccount) query.uniqueResult();
    }

    public void updateAccount(BankAccount applicantBankAccount) {

        getSession().update(applicantBankAccount);
    }

    public BankAccount createAccount(BankAccount bankAccount) {
        getSession().save(bankAccount);
        return (bankAccount);
    }

    public void deleteAccount(BankAccount bankAccount) {
        getSession().delete(bankAccount);
    }

    @Override
    public void saveUserTicket(UserTicket userTicket) {
        getSession().save(userTicket);
    }

    @Override
    public void removeAccount(String accountId) {

        //BankAccount account = loadAccountByAccountNumber(accountId);
        //getSession().delete(account);

        Query query = getSession().createSQLQuery("delete from BANK_ACCOUNT where ID=:accountId");
        query.setParameter("accountId", accountId);
        query.executeUpdate();
    }

}
