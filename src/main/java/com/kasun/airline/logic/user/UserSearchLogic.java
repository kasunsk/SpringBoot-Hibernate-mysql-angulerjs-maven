package com.kasun.airline.logic.user;

import com.kasun.airline.common.dto.UserSearchCriteria;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kasun on 3/3/17.
 */
@Component
public class UserSearchLogic extends StatelessServiceLogic<List<User>, UserSearchCriteria> {

    @Autowired
    private UserDao userHibernateDao;

    @Override
    public List<User> invoke(UserSearchCriteria searchCriteria) {
        return userHibernateDao.searchUserByCriteria(searchCriteria);
    }
}
