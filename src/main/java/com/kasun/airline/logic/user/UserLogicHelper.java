package com.kasun.airline.logic.user;

import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class UserLogicHelper {

    @Autowired
    private UserDao userHibernateDao;

    protected User loadUser(LoginRequest loginRequest) {

        User user = userHibernateDao.loadUserByEmail(loginRequest.getEmail());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }


}
