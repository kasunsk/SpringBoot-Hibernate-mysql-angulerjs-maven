package com.kasun.ailine.service.common.service;

import com.kasun.ailine.service.common.dto.ServiceRequest;
import com.kasun.ailine.service.common.dto.ServiceResponse;
import com.kasun.ailine.service.common.execption.Error;
import com.kasun.ailine.service.common.execption.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kasun on 2/28/17.
 */
public abstract class RequestAssembler {

    private static final Logger logger = LoggerFactory.getLogger(RequestAssembler.class);

    @SuppressWarnings("unchecked")
    public static ServiceResponse assemble(ServiceLogic logic, ServiceRequest request) {

        ServiceResponse serviceResponse = new ServiceResponse();

        try {
            Object result = logic.invoke(request);
            serviceResponse.setPayload(result);
        } catch (ServiceRuntimeException ex) {
            logger.error(ex.getMessage(), ex);
            addError(serviceResponse, ex);
        }
        return serviceResponse;
    }

    private static void addError(ServiceResponse serviceResponse, ServiceRuntimeException ex) {
        com.kasun.ailine.service.common.execption.Error error = new Error();
        error.setErrorMsg(ex.getMessage());
        serviceResponse.setError(error);
    }
}
