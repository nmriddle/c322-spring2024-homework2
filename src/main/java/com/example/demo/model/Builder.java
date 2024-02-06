package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Builder {
    FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY;

    @JsonCreator
    public static Builder fromText(String text) {
        for (Builder builder : Builder.values()) {
            if (builder.toString().equalsIgnoreCase(text)) {
                return builder;
            }
        }
        // Default case or handle as needed
        return Builder.ANY;
    }

    public String toString() {
        switch (this) {
            case FENDER:
                return "Fender";
            case MARTIN:
                return "Martin";
            case GIBSON:
                return "Gibson";
            case COLLINGS:
                return "Collings";
            case OLSON:
                return "Olson";
            case RYAN:
                return "Ryan";
            case PRS:
                return "PRS";
            default:
                return "Unspecified";
        }
    }
}