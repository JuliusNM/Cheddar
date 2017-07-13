package com.example.dell.cheddar.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 7/12/2017.
 */

public class ResponseData {

    @SerializedName("id")
    private String id;

    public ResponseData(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
