package com.example.dell.cheddar.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 7/10/2017.
 */

public class Recipient implements Serializable {

    private String firstName;
    private String lastName;
    private String bank;
    private String country;
    private String accountNumber;
  //  private String phoneNumber;



    public Recipient(String firstName, String lastName, String bank, String country, String accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bank = bank;
        this.country = country;
        this.accountNumber = accountNumber;
      //  this.phoneNumber = phoneNumber;


    }

    public String getName(){
        return firstName+" "+lastName;
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
//    public String getPhoneNumber() {return phoneNumber;}
//
//    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
