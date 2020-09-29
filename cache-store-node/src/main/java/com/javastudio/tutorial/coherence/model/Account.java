package com.javastudio.tutorial.coherence.model;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 526175882572859631L;

    private Long accountIsn;
    private String branch;
    private String module;
    private String scheme;
    private String accountNo;
    private String status;

    public Account() {

    }

    public Account(Long id) {
        this.accountIsn = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAccountIsn() {
        return accountIsn;
    }

    public void setAccountIsn(Long accountIsn) {
        this.accountIsn = accountIsn;
    }
}
