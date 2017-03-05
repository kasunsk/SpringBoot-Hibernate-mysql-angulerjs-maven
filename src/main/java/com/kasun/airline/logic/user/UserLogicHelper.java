package com.kasun.airline.logic.user;

import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This helper class is provide all the common methods for User Logic classes.
 */
@Component
public class UserLogicHelper {

    @Autowired
    private UserDao userHibernateDao;

    protected User loadUser(String email) {

        User user = userHibernateDao.loadUserByEmail(email);

        if (user == null) {
            throw new ServiceRuntimeException(ErrorCode.USER_NOT_FOUND, "User not found");
        }
        return user;
    }


}
