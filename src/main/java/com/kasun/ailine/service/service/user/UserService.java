
package com.kasun.ailine.service.service.user;

import com.kasun.ailine.service.dto.user.LoginRequest;
import com.kasun.ailine.service.common.dto.UserSearchCriteria;
import com.kasun.ailine.service.model.user.User;

import java.util.List;

/**
 * Created by kasun on 1/28/17.
 */
public interface UserService {

    String saveUser(User user);

    List<User> retrieveAllUsers();

    void removeUser(String userId);

    Boolean isUserNameUnique(Long userId, String userName);

    User login(LoginRequest loginRequest);

    void authenticateUser(String applicantId);

    User loadUserById(String applicantId);

    List<User> searchUser(UserSearchCriteria searchCriteria);
}
