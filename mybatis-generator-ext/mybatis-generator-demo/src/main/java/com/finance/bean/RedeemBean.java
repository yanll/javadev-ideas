package com.finance.bean;

import java.math.BigDecimal;
import java.util.Date;

public class RedeemBean {
    private Long id;

    private Long investorId;

    private String thirtpartyAccountNo;

    private String redeemType;

    private String redeemChannel;

    private BigDecimal amount;

    private BigDecimal actualSettlementAmount;

    private Integer serviceRateId;

    private BigDecimal serviceFee;

    private String fundCode;

    private String status;

    private String fundType;

    private String externalRef;

    private String redeemOrderNo;

    private String pilFlowNo;

    private String pilReversalNo;

    private String pilResultFlag;

    private String errorCode;

    private String errorMsg;

    private String bankCode;

    private String bankAccount;

    private String bankAccountMask;

    private String memo;

    private Date createDatetime;

    private String modifyDate;

    private Date finishDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public String getThirtpartyAccountNo() {
        return thirtpartyAccountNo;
    }

    public void setThirtpartyAccountNo(String thirtpartyAccountNo) {
        this.thirtpartyAccountNo = thirtpartyAccountNo;
    }

    public String getRedeemType() {
        return redeemType;
    }

    public void setRedeemType(String redeemType) {
        this.redeemType = redeemType;
    }

    public String getRedeemChannel() {
        return redeemChannel;
    }

    public void setRedeemChannel(String redeemChannel) {
        this.redeemChannel = redeemChannel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualSettlementAmount() {
        return actualSettlementAmount;
    }

    public void setActualSettlementAmount(BigDecimal actualSettlementAmount) {
        this.actualSettlementAmount = actualSettlementAmount;
    }

    public Integer getServiceRateId() {
        return serviceRateId;
    }

    public void setServiceRateId(Integer serviceRateId) {
        this.serviceRateId = serviceRateId;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getExternalRef() {
        return externalRef;
    }

    public void setExternalRef(String externalRef) {
        this.externalRef = externalRef;
    }

    public String getRedeemOrderNo() {
        return redeemOrderNo;
    }

    public void setRedeemOrderNo(String redeemOrderNo) {
        this.redeemOrderNo = redeemOrderNo;
    }

    public String getPilFlowNo() {
        return pilFlowNo;
    }

    public void setPilFlowNo(String pilFlowNo) {
        this.pilFlowNo = pilFlowNo;
    }

    public String getPilReversalNo() {
        return pilReversalNo;
    }

    public void setPilReversalNo(String pilReversalNo) {
        this.pilReversalNo = pilReversalNo;
    }

    public String getPilResultFlag() {
        return pilResultFlag;
    }

    public void setPilResultFlag(String pilResultFlag) {
        this.pilResultFlag = pilResultFlag;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccountMask() {
        return bankAccountMask;
    }

    public void setBankAccountMask(String bankAccountMask) {
        this.bankAccountMask = bankAccountMask;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getFinishDatetime() {
        return finishDatetime;
    }

    public void setFinishDatetime(Date finishDatetime) {
        this.finishDatetime = finishDatetime;
    }
}