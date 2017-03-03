package com.kasun.airline.dao.user;

import com.kasun.airline.common.dto.UserSearchCriteria;
import com.kasun.airline.model.user.User;

import java.util.List;

/**
 * This interface provide the database access methods
 */
public interface UserDao {

    void saveUser(User user);

    List<User> allUsers();

    User loadUserByEmail(String email);

    User loadUserById(String applicantId);

    List<User> searchUserByCriteria(UserSearchCriteria searchCriteria);

    void remove(String userId);
}
