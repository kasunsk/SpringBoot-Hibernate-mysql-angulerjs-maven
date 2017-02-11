
package com.crossover.techtrial.java.se.service.user;

import com.crossover.techtrial.java.se.dto.LoginRequest;
import com.crossover.techtrial.java.se.model.user.User;

import java.util.List;

/**
 * Created by kasun on 1/28/17.
 */
public interface UserService {

    String saveUser(User user);

    List<User> retrieveAllUsers();

    Boolean isUserNameUnique(Long userId, String userName);

    String login(LoginRequest loginRequest);

    void authenticateUser(String applicantId);
}
