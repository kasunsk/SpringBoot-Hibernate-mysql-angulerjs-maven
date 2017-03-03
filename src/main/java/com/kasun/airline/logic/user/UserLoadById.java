package com.kasun.airline.logic.user;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class UserLoadById extends StatelessServiceLogic<User, String> {

    @Autowired
    private UserDao userHibernateDao;

    @Transactional
    @Override
    public User invoke(String applicantId) {
        return userHibernateDao.loadUserById(applicantId);
    }
}
