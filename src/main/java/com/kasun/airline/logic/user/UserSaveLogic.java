package com.kasun.airline.logic.user;

import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.dto.user.UserRole;
import com.kasun.airline.model.user.User;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserSaveLogic extends StatelessServiceLogic<String, User> {

    @Autowired
    private UserDao userHibernateDao;

    @Transactional
    @Override
    public String invoke(User user) {
        validateUser(user);
        userHibernateDao.saveUser(user);
        return user.getName();
    }

    private void validateUser(User user) {

        ValidationUtil.validate(user, "User can not be empty");
        ValidationUtil.validate(user.getEmail(), "Email can not be emplty");
        ValidationUtil.validate(user.getName(), "Name can not be empty");
        ValidationUtil.validate(user.getPassword(), "Password can not be empty");

        if (user.getRole() == null) {
            user.setRole(UserRole.USER);
        }

        User currentUser = userHibernateDao.loadUserByEmail(user.getEmail());

        if (currentUser != null) {
            throw new ServiceRuntimeException(ErrorCode.USER_ALREADY_EXIST, "User already exist");
        }
    }
}
