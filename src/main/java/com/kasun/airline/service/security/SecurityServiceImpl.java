package com.kasun.airline.service.security;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.common.service.RequestAssembler;
import com.kasun.airline.logic.security.DataEncryptLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kasun on 3/5/17.
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private DataEncryptLogic dataEncryptLogic;

    @Override
    public ServiceResponse<String> encrypt(ServiceRequest<String> word) {
        return RequestAssembler.assemble(dataEncryptLogic, word);
    }
}
