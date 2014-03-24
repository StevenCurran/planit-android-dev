package com.planit;

/**
 * Created by Garf on 26/02/2014.
 */
public class LinkedAccount {

    public String accountId;
    public String accountProvider;
    public Boolean displayInCal;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String name) {
        this.accountId = accountId;
    }

    public String getAccountProvider() {
        return accountProvider;
    }

    public void setAccountProvider(String name) {
        this.accountProvider = accountProvider;
    }

    public Boolean getDisplayInCal() {
        return displayInCal;
    }

    public void setDisplayInCal(Boolean displayInCal) {
        this.displayInCal = displayInCal;
    }

}
