package com.arpannandi.shoppingapp.dto;

import java.io.Serializable;

public class SearchDto implements Serializable {
    private String phrase;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
