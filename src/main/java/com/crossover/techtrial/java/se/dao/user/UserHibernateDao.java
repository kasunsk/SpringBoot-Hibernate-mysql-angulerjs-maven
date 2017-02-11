package com.crossover.techtrial.java.se.dao.user;

import com.crossover.techtrial.java.se.dao.AbstractDao;
import com.crossover.techtrial.java.se.model.user.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kasun on 1/28/17.
 */
@Repository
public class UserHibernateDao extends AbstractDao<Long,User> implements UserDao {

    public void saveUser(User user) {
        getSession().save(user);
    }

    @Override
    public List<User> allUsers() {
        Query query = getSession().createQuery("from User");
        return query.list();
    }

    @Override
    public User loadUserByEmail(String email) {

        Query query = getSession().createQuery("from User U where U.email=:email");
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }


    @Override
    public User getUserById(String applicantId) {

        Query query = getSession().createQuery("from User U where U.userId=:userId");
        query.setParameter("userId", Long.parseLong(applicantId));
        return (User) query.uniqueResult();
    }
}
