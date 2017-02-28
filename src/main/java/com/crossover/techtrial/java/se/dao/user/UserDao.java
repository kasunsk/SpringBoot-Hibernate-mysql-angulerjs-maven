package com.crossover.techtrial.java.se.dao.user;

import com.crossover.techtrial.java.se.common.dto.UserSearchCriteria;
import com.crossover.techtrial.java.se.model.user.User;

import java.util.List;

/**
 * Created by kasun on 1/28/17.
 */
public interface UserDao {

    void saveUser(User user);

    List<User> allUsers();

    User loadUserByEmail(String email);

    User loadUserById(String applicantId);

    List<User> searchUserByCriteria(UserSearchCriteria searchCriteria);

    void remove(String userId);
}
