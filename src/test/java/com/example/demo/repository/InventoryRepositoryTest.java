package com.example.demo.repository;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    @Test
    void addGuitar1() {
        // add 2 different guitars and make sure the file includes them
        InventoryRepository inventoryRepository = new InventoryRepository();
        inventoryRepository.addGuitar("123456", 999.99, "Fender", "Stratocaster", "Electric", "Maple", "Alder");
        String filePath = "guitars_database.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            assertEquals(br.readLine(), "123456,999.99,Fender,Stratocaster,Electric,Maple,Alder");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        inventoryRepository.addGuitar("789012", 1499.99, "Gibson", "Les Paul", "Electric", "Mahogany", "Maple");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            assertEquals(br.readLine(), "789012,1499.99,Gibson,Les Paul,Electric,Mahogany,Maple");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addGuitar2() {
        // makes sure redundant serial numbers can't be added and file is cleared when new inventory made
        InventoryRepository inventoryRepository = new InventoryRepository();
        inventoryRepository.addGuitar("123456", 999.99, "Fender", "Stratocaster", "Electric", "Maple", "Alder");
        String filePath = "guitars_database.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            assertEquals(br.readLine(), "123456,999.99,Fender,Stratocaster,Electric,Maple,Alder");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // it won't be added again
        inventoryRepository.addGuitar("123456", 1499.99, "Gibson", "Les Paul", "Electric", "Mahogany", "Maple");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            assertEquals(br.readLine(), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // the file will be empty
        inventoryRepository = new InventoryRepository();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            assertEquals(br.readLine(), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getGuitar() {

    }

    @Test
    void search() {
    }
}