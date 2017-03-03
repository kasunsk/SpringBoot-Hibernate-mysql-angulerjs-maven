package com.kasun.airline.logic.user;

import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
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
public class UserAuthenticateLogic extends StatelessServiceLogic<Void, String> {

    @Autowired
    private UserDao userHibernateDao;

    @Transactional
    @Override
    public Void invoke(String applicantId) {
        User user = userHibernateDao.loadUserById(applicantId);

        if (user == null) {
            throw new ServiceRuntimeException(ErrorCode.USER_NOT_FOUND, "Applicant not exist");
        }
        return new Void();
    }
}
