package com.example.demo.repository;

import com.example.demo.model.Builder;
import com.example.demo.model.Guitar;
import com.example.demo.model.Type;
import com.example.demo.model.Wood;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {
    private List<Guitar> guitars;

    public InventoryRepository() {
        guitars = new ArrayList<>();
        try {
            FileWriter writer = new FileWriter("guitars_database.txt", false);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void addGuitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backWood, Wood topWood) {
        /**
         * takes variables and appends guitar to the list and file.
         */
        if (getGuitar(serialNumber) != null) {
            // cannot add the same serial number twice
            return;
        }
        Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        guitars.add(guitar);
        try {
            FileWriter writer = new FileWriter("guitars_database.txt", true);
            writer.write(serialNumber + "," + price + "," + builder + "," + model + "," + type + "," + backWood + "," + topWood + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Guitar getGuitar(String serialNumber) {
        /**
         * checks if a guitar of a given serial number exists, returns null otherwise
         */
        for (Guitar guitar : guitars) {
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public List<Guitar> search(Guitar searchGuitar) {
        /**
         * returns all guitars with the properties of searchGuitar (skips null ones)
         *
         */
        List<Guitar> searchGuitars = new ArrayList<>();
        for (Guitar guitar : guitars) {
            if (searchGuitar.getSerialNumber() != null && !searchGuitar.getSerialNumber().equals(guitar.getSerialNumber())) {
                continue;
            }
            if (searchGuitar.getBuilder() != null && !searchGuitar.getBuilder().equals(guitar.getBuilder())) {
                continue;
            }
            if (searchGuitar.getPrice() != null && !searchGuitar.getPrice().equals(guitar.getPrice())) {
                continue;
            }
            if (searchGuitar.getModel() != null && !searchGuitar.getModel().equals(guitar.getModel())) {
                continue;
            }
            if (searchGuitar.getType() != null && !searchGuitar.getType().equals(guitar.getType())) {
                continue;
            }
            if (searchGuitar.getBackWood() != null && !searchGuitar.getBackWood().equals(guitar.getBackWood())) {
                continue;
            }
            if (searchGuitar.getTopWood() != null && !searchGuitar.getTopWood().equals(guitar.getTopWood())) {
                continue;
            }
            searchGuitars.add(guitar);
        }
        return searchGuitars;
    }
}