
package com.kasun.airline.service.user;

import com.kasun.airline.common.dto.*;
import com.kasun.airline.common.dto.Void;
import com.kasun.airline.dto.user.LoginRequest;
import com.kasun.airline.model.user.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by kasun on 1/28/17.
 */
public interface UserService {

    ServiceResponse<String> saveUser(ServiceRequest<User> user);

    ServiceResponse<List<User>> retrieveAllUsers(ServiceRequest<com.kasun.airline.common.dto.Void> voidServiceRequest);

    ServiceResponse<Void> removeUser(ServiceRequest<String> userId);

    ServiceResponse<User> login(ServiceRequest<LoginRequest> loginRequest);

    ServiceResponse<Void> authenticateUser(ServiceRequest<String> applicantId);

    ServiceResponse<User> loadUserById(ServiceRequest<String> applicantId);

    ServiceResponse<List<User>> searchUser(ServiceRequest<UserSearchCriteria> criteria);
}
