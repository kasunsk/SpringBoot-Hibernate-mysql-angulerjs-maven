package com.kasun.airline.common.service;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;

/**
 *  This class assemble related business logic with request. Help to decouple business logic from service.
 *  will execute invoke method in every logic classes.
 */
public abstract class RequestAssembler {

    @SuppressWarnings("unchecked")
    public static <T, V> ServiceResponse<T> assemble(ServiceLogic<T, V> logic, ServiceRequest<V> serviceRequest) {

        ServiceResponse serviceResponse = new ServiceResponse();

        Object result = logic.invoke(serviceRequest.getPayload());
        serviceResponse.setPayload(result);
        return serviceResponse;
    }
}
