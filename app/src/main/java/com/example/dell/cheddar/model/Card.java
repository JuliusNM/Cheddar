package com.example.dell.cheddar.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 7/15/2017.
 */

public class Card implements Serializable, AccountInterface {

    @SerializedName("CardNumber")
    private String cardNumber;
    @SerializedName("CardIssuer")
    private String cardIssuer;
    @SerializedName("CVV")
    private String cvv;
    @SerializedName("ExpiryYear")
    private String expiryYear;
    @SerializedName("ExpiryMonth")
    private String expiryMonth;

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }


    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Card(String cardIssuer, String cardNumber, String cvv, String expiryMonth, String expiryYear){

        this.cardIssuer = cardIssuer;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;

    }
    public String toString(){
        return this.getCardIssuer()+"\n"+"Card Number:"+this.getCardNumber();
    }

}
