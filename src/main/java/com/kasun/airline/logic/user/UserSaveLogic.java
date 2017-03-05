package com.kasun.airline.logic.user;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.dto.user.UserRole;
import com.kasun.airline.model.user.User;
import com.kasun.airline.service.security.SecurityService;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Component
public class UserSaveLogic extends StatelessServiceLogic<String, User> {

    @Autowired
    private UserDao userHibernateDao;

    @Autowired
    private SecurityService securityService;

    @Transactional
    @Override
    public String invoke(User user) {
        validateUser(user);
        encryptUserPassword(user);
        userHibernateDao.saveUser(user);
        return user.getName();
    }

    private void encryptUserPassword(User user) {

        String encryptedPassword = securityService.encrypt(new ServiceRequest<>(user.getPassword())).getPayload();
        user.setPassword(encryptedPassword);
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
