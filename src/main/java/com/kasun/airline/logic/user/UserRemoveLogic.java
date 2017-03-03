package com.kasun.airline.logic.user;

import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class UserRemoveLogic extends StatelessServiceLogic<com.kasun.airline.common.dto.Void, String> {

    @Autowired
    private UserDao userHibernateDao;

    @Transactional
    @Override
    public com.kasun.airline.common.dto.Void invoke(String userId) {
        userHibernateDao.remove(userId);
        return new Void();
    }
}
