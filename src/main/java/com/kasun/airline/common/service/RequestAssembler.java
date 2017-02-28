package com.kasun.airline.common.service;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.common.execption.Error;
import com.kasun.airline.common.execption.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kasun on 2/28/17.
 */
public abstract class RequestAssembler {

    private static final Logger logger = LoggerFactory.getLogger(RequestAssembler.class);

    @SuppressWarnings("unchecked")
    public static <T,V> ServiceResponse<T> assemble(ServiceLogic<T,V> logic, ServiceRequest<V> serviceRequest) {

        ServiceResponse serviceResponse = new ServiceResponse();

        try {
            Object result = logic.invoke(serviceRequest.getPayload());
            serviceResponse.setPayload(result);
        } catch (ServiceRuntimeException ex) {
            logger.error(ex.getMessage(), ex);
            addError(serviceResponse, ex);
        }
        return serviceResponse;
    }

    private static void addError(ServiceResponse serviceResponse, ServiceRuntimeException ex) {
        com.kasun.airline.common.execption.Error error = new Error();
        error.setErrorMsg(ex.getMessage());
        serviceResponse.setError(error);
    }
}
