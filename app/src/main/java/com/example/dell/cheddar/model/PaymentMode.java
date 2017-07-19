package com.example.dell.cheddar.model;

import java.util.ArrayList;

/**
 * Created by Dell on 7/17/2017.
 */

public class PaymentMode {
    /*
        type
        ArryaList of paymentoptons in that type
    * */


    private String type;
    private ArrayList<String> options;

    public PaymentMode(String type, ArrayList<String> options) {
        this.type = type;
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public PaymentMode() {
    }
}
