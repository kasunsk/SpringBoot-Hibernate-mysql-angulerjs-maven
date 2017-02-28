package com.kasun.ailine.service.dto.account;

/**
 * Created by kasun on 2/4/17.
 */
public class MoneyTransferRequest {

    public enum TransferType {
        DEPOSIT, WITHDRAW
    }

    private String accountNumber;
    private Double transferAmount;
    private TransferType transferType;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }
}
