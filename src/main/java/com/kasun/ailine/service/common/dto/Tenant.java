package com.kasun.ailine.service.common.dto;

/**
 * Created by kasun on 2/28/17.
 */
public class Tenant {

    private String tenantCode;
    private String tenantName;

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
