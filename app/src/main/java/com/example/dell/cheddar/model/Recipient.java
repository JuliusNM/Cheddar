package com.example.dell.cheddar.model;

import android.media.Image;

/**
 * Created by Dell on 7/10/2017.
 */

public class Recipient {
    private String firstName;
    private String lastName;
    private String bank;
    private String country;
    private String acountNumber;
    private Integer profile;

    public Recipient(String firstName, String lastName, String bank, String country, String acountNumber, Integer profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bank = bank;
        this.country = country;
        this.acountNumber = acountNumber;
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

    public String getAcountNumber() {
        return acountNumber;
    }

    public void setAcountNumber(String acountNumber) {
        this.acountNumber = acountNumber;
    }
}
