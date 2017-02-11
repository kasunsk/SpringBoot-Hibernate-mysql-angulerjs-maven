package com.crossover.techtrial.java.se.service.user;

import com.crossover.techtrial.java.se.dao.user.UserDao;
import com.crossover.techtrial.java.se.dto.LoginRequest;
import com.crossover.techtrial.java.se.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

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
        return userHibernateDao.allUsers();
    }

    @Transactional
    public String login(LoginRequest loginRequest) {

        User user = loadUser(loginRequest);
        validateLogin(loginRequest, user);
        return user.getUserId().toString();
    }

    @Transactional
    public void authenticateUser(String applicantId) {

        User user = userHibernateDao.getUserById(applicantId);

        if (user == null) {
            throw new RuntimeException("Applicant not found");
        }
    }

    private User loadUser(LoginRequest loginRequest) {
        return userHibernateDao.loadUserByEmail(loginRequest.getEmail());
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


    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public Boolean isUserNameUnique(Long userId, String userName0) {
        return null;
    }

    private void validateUser(User user) {

        if (user != null) {

            if (user.getRoleId() == null) {
                user.setRoleId(2L);
            }
        }
    }
}
