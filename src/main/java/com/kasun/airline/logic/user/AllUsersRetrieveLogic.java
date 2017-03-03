package com.kasun.airline.logic.user;


import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class AllUsersRetrieveLogic extends StatelessServiceLogic<List<User>, Void> {

    @Autowired
    private UserDao userHibernateDao;

    @Transactional
    @Override
    public List<User> invoke(Void var) {
        List<User> users = userHibernateDao.allUsers();
        return hideUnwanted(users);
    }

    private List<User> hideUnwanted(List<User> users) {

        for (User user : users) {
            user.setUserBankAccounts(null);
        }
        return users;
    }
}
