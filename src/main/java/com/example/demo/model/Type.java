package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {

    ACOUSTIC, ELECTRIC, ANY;

    @JsonCreator
    public static Type fromText(String text) {
        for (Type type : Type.values()) {
            if (type.toString().equalsIgnoreCase(text)) {
                return type;
            }
        }
        // Default case or handle as needed
        return Type.ANY;
    }

    public String toString() {
        switch (this) {
            case ACOUSTIC:
                return "Acoustic";
            case ELECTRIC:
                return "Electric";
            default:
                return "unspecified";
        }
    }
}
