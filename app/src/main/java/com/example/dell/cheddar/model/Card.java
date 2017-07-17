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

    public Card(String cardIssuer, String cardNumber){

        this.cardIssuer = cardIssuer;
        this.cardNumber = cardNumber;

    }

}
