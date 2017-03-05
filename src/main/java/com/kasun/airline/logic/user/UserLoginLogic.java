package com.kasun.airline.logic.user;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.model.user.User;
import com.kasun.airline.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserLoginLogic extends StatelessServiceLogic<User, LoginRequest> {

    @Autowired
    private UserLogicHelper logicHelper;

    @Autowired
    private SecurityService securityService;

    @Transactional
    @Override
    public User invoke(LoginRequest loginRequest) {

        User user = logicHelper.loadUser(loginRequest.getEmail());
        validateLogin(loginRequest, user);
        user.setUserBankAccounts(null);
        return user;
    }

    private void validateLogin(LoginRequest loginRequest, User user) {

        if (user == null) {
            throw new RuntimeException("User not exist");
        }

        String encryptedPassword = securityService.encrypt(new ServiceRequest<>(loginRequest.getPassword())).getPayload();

        if (!user.getPassword().equals(encryptedPassword)) {
            throw new RuntimeException("Login invalid");
        }
    }
}
