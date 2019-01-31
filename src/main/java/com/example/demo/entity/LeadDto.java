package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LeadDto {

    @NotBlank
    private String firstName;

    private String lastName;

    private Long leadId;

    @NotNull
    private String primaryPhoneNumber;

    private String email;

    private String source;

    private Date trucallerTs;

    private Double score;

    private String referralCode;

    private String reasonForLoan;

    private boolean registered;

    private String salesAssociateId;

    private String loanManagerId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getReasonForLoan() {
        return reasonForLoan;
    }

    public void setReasonForLoan(String reasonForLoan) {
        this.reasonForLoan = reasonForLoan;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean isRegistered) {
        this.registered = isRegistered;
    }

    public String getSalesAssociateId() {
        return salesAssociateId;
    }

    public void setSalesAssociateId(String salesAssociateId) {
        this.salesAssociateId = salesAssociateId;
    }

    public String getLoanManagerId() {
        return loanManagerId;
    }

    public void setLoanManagerId(String loanManagerId) {
        this.loanManagerId = loanManagerId;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public Date getTrucallerTs() {
        return trucallerTs;
    }

    public void setTrucallerTs(Date trucallerTs) {
        this.trucallerTs = trucallerTs;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
