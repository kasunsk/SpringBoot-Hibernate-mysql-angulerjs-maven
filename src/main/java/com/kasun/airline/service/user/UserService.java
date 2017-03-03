
package com.kasun.airline.service.user;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.model.user.User;

import java.util.List;

/**
 * This API will provide all the service methods for UserService
 */
public interface UserService {

    /**
     *
     * @param user
     * @return name of created user
     */
    ServiceResponse<String> saveUser(ServiceRequest<User> user);

    /**
     *
     * @param voidServiceRequest
     * @return List of all users, This service method only for admin
     */
    ServiceResponse<List<User>> retrieveAllUsers(ServiceRequest<com.kasun.airline.common.dto.Void> voidServiceRequest);

    /**
     *
     * @param userId
     * @return void
     */
    ServiceResponse<Void> removeUser(ServiceRequest<String> userId);

    /**
     *
     * @param loginRequest
     * @return logged in user
     */
    ServiceResponse<User> login(ServiceRequest<LoginRequest> loginRequest);

    /**
     *
     * @param applicantId
     * @return void
     */
    ServiceResponse<Void> authenticateUser(ServiceRequest<String> applicantId);

    /**
     *
     * @param applicantId
     * @return user
     */
    ServiceResponse<User> loadUserById(ServiceRequest<String> applicantId);

    /**
     *
     * @param criteria
     * @return List of Users according to search criteria
     */
    ServiceResponse<List<User>> searchUser(ServiceRequest<UserSearchCriteria> criteria);
}
