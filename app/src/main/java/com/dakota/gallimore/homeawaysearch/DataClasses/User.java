package com.dakota.gallimore.homeawaysearch.DataClasses;

/**
 * Created by galli_000 on 11/2/2017.
 * Class keeping track of the current Users information.
 */

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String[] accountTypes;
    private long phoneNum;
    private String id;
    private String site;

    public User() {
        firstName = "Error";
        lastName = "Error";
        this.email = "Error@Error.com";
        accountTypes = new String[]{""};
        this.id = "";
        site = "";
        phoneNum = 0;

    }

    public User(String first, String last, String email, String[] types, String id, String homeSite, long phone) {
        firstName = first;
        lastName = last;
        this.email = email;
        accountTypes = types;
        this.id = id;
        site = homeSite;
        phoneNum = phone;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(String[] accountType) {
        this.accountTypes = accountType;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}