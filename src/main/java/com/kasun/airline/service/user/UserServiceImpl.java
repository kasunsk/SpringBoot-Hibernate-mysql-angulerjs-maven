package com.kasun.airline.service.user;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.common.service.RequestAssembler;
import com.kasun.airline.dao.user.UserDao;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.dto.user.UserRole;
import com.kasun.airline.logic.user.*;
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
    private UserSaveLogic userSaveLogic;

    @Autowired
    private AllUsersRetrieveLogic allUsersRetrieveLogic;

    @Autowired
    private UserRemoveLogic userRemoveLogic;

    @Autowired
    private UserLoginLogic userLoginLogic;

    @Autowired
    private UserAuthenticateLogic userAuthenticateLogic;

    @Autowired
    private UserSearchLogic userSearchLogic;

    @Autowired
    private UserLoadById userLoadById;

    @Transactional
    public ServiceResponse<String> saveUser(ServiceRequest<User> user) {

        return RequestAssembler.assemble(userSaveLogic, user);
    }

    @Override
    public ServiceResponse<List<User>> retrieveAllUsers(ServiceRequest<Void> voidServiceRequest) {

        return RequestAssembler.assemble(allUsersRetrieveLogic, voidServiceRequest);
    }

    @Override
    public ServiceResponse<Void> removeUser(ServiceRequest<String> userId) {

        return RequestAssembler.assemble(userRemoveLogic, userId);
    }

    @Override
    public ServiceResponse<User> login(ServiceRequest<LoginRequest> loginRequest) {

        return RequestAssembler.assemble(userLoginLogic, loginRequest);
    }

    @Override
    public ServiceResponse<Void> authenticateUser(ServiceRequest<String> applicantId) {

        return RequestAssembler.assemble(userAuthenticateLogic, applicantId);
    }

    @Override
    public ServiceResponse<User> loadUserById(ServiceRequest<String> applicantId) {

        return RequestAssembler.assemble(userLoadById, applicantId);
    }

    @Override
    public ServiceResponse<List<User>> searchUser(ServiceRequest<UserSearchCriteria> criteria) {

        return RequestAssembler.assemble(userSearchLogic, criteria);
    }
}
