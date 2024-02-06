package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Wood {
    INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO, CEDAR, ADIRONDACK, ALDER, SITKA, ANY;

    @JsonCreator
    public static Wood fromText(String text) {
        for (Wood wood : Wood.values()) {
            if (wood.toString().equalsIgnoreCase(text)) {
                return wood;
            }
        }
        // Default case or handle as needed
        return Wood.ANY;
    }

    public String toString() {
        switch (this) {
            case INDIAN_ROSEWOOD:
                return "Indian Rosewood";
            case BRAZILIAN_ROSEWOOD:
                return "Brazilian Rosewood";
            case MAHOGANY:
                return "Mahogany";
            case MAPLE:
                return "Maple";
            case COCOBOLO:
                return "Cocobolo";
            case CEDAR:
                return "Cedar";
            case ADIRONDACK:
                return "Adirondack";
            case ALDER:
                return "Alder";
            case SITKA:
                return "Sitka";
            default:
                return "unspecified";
        }
    }
}