package com.kasun.airline.service.user;

import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.dto.user.UserRole;
import com.kasun.airline.common.dto.UserSearchCriteria;
import com.kasun.airline.model.user.User;
import com.kasun.airline.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kasun.airline.util.ValidationUtil.validate;

/**
 * Created by kasun on 1/28/17.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userHibernateDao;

    @Transactional
    public String saveUser(User user) {
        validateUser(user);
        userHibernateDao.saveUser(user);
        return user.getName();
    }

    @Transactional
    public List<User> retrieveAllUsers() {
        List<User> users = userHibernateDao.allUsers();
        return hideUnwanted(users);
    }

    @Transactional
    @Override
    public void removeUser(String userId) {
        userHibernateDao.remove(userId);
    }

    private List<User> hideUnwanted(List<User> users) {

        for (User user : users) {
            user.setUserBankAccounts(null);
        }
        return users;
    }

    @Transactional
    public User login(LoginRequest loginRequest) {

        User user = loadUser(loginRequest);
        validateLogin(loginRequest, user);
        prepareToSend(user);
        return user;
    }

    private void prepareToSend(User user) {
        user.setUserBankAccounts(null);
    }

    @Transactional
    public void authenticateUser(String applicantId) {

        User user = userHibernateDao.loadUserById(applicantId);

        if (user == null) {
            throw new RuntimeException("Applicant not found");
        }
    }

    @Override
    @Transactional
    public User loadUserById(String applicantId) {
        return userHibernateDao.loadUserById(applicantId);
    }

    @Override
    @Transactional
    public List<User> searchUser(UserSearchCriteria searchCriteria) {
        return userHibernateDao.searchUserByCriteria(searchCriteria);
    }

    private User loadUser(LoginRequest loginRequest) {

        User user = userHibernateDao.loadUserByEmail(loginRequest.getEmail());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    private void validateLogin(LoginRequest loginRequest, User user) {

        if (user == null) {
            throw new RuntimeException("User not exist");
        }

        //TODO password should be encrypted
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Login invalid");
        }
    }

    public Boolean isUserNameUnique(Long userId, String userName0) {
        return null;
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
            throw new RuntimeException("User alredy available");
        }
    }
}
