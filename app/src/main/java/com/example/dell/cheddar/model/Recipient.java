package com.example.dell.cheddar.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 7/10/2017.
 */

public class Recipient implements Serializable {
    @SerializedName("RecipientFirstName")
    private String firstName;
    @SerializedName("RecipientLastName")
    private String lastName;
    @SerializedName("RecipientBank")
    private String bank;
    @SerializedName("Country")
    private String country;
    @SerializedName("RecipientAccountNumber")
    private String accountNumber;
    private Integer profile;

    public Recipient(String firstName, String lastName, String bank, String country, String accountNumber, Integer profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bank = bank;
        this.country = country;
        this.accountNumber = accountNumber;
        this.profile = profile;
    }

    public String getName(){
        return firstName+" "+lastName;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
