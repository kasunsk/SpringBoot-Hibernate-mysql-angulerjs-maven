package com.kasun.airline.logic.user;

import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserLoginLogic extends StatelessServiceLogic<User, LoginRequest> {

    @Autowired
    private UserLogicHelper logicHelper;

    @Transactional
    @Override
    public User invoke(LoginRequest loginRequest) {

        User user = logicHelper.loadUser(loginRequest);
        validateLogin(loginRequest, user);
        user.setUserBankAccounts(null);
        return user;
    }

    private void validateLogin(LoginRequest loginRequest, User user) {

        if (user == null) {
            throw new RuntimeException("User not exist");
        }

        //TODO password should be encrypted
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Login invalid");
        }
    }
}
