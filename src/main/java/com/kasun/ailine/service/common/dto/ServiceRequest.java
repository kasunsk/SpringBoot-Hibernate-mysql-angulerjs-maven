package com.kasun.ailine.service.common.dto;

/**
 * Created by kasun on 2/28/17.
 */
public class ServiceRequest<T> {

    private T payload;
    private Tenant tenant;

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
