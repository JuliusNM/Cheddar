package com.example.dell.cheddar.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 7/13/2017.
 */

public class Bank implements Serializable, AccountInterface {


    @SerializedName("BankName")
    private String bank;
    @SerializedName("AccountNumber")
    private String accountNumber ;


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Bank(String bank, String accountNumber){
        this.bank = bank;
        this.accountNumber = accountNumber;

    }
    public  String toString(){
        return this.getBank()+" "+this.getAccountNumber();
    }
}
