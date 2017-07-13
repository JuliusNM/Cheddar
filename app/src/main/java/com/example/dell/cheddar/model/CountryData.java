package com.example.dell.cheddar.model;

/**
 * Created by Dell on 6/26/2017.
 */

public class CountryData {

    String text;
    Integer imageId;
    public CountryData(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }

    @Override
    public String toString() {
        return text;
    }
}
