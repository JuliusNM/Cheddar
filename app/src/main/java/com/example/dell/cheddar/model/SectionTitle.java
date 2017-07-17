package com.example.dell.cheddar.model;

/**
 * Created by Dell on 7/16/2017.
 */

public class SectionTitle implements AccountInterface {
    private String title;

    public SectionTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
