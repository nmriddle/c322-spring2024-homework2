package com.example.demo.repository;

import com.example.demo.model.Builder;
import com.example.demo.model.Guitar;
import com.example.demo.model.Type;
import com.example.demo.model.Wood;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    @Test
    void addGuitar1() {
        // add 2 different guitars and make sure the file includes them
        InventoryRepository inventoryRepository = new InventoryRepository();
        inventoryRepository.addGuitar("123456", 999.99, Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.MAPLE, Wood.ALDER);
        String filePath = "guitars_database.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            assertEquals(br.readLine(), "123456,999.99,Fender,Stratocaster,Electric,Maple,Alder");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        inventoryRepository.addGuitar("789012", 1499.99, Builder.GIBSON, "Les Paul", Type.ELECTRIC, Wood.MAPLE, Wood.MAPLE);
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
        inventoryRepository.addGuitar("123456", 999.99, Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.MAPLE, Wood.MAPLE);
        String filePath = "guitars_database.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            assertEquals(br.readLine(), "123456,999.99,Fender,Stratocaster,Electric,Maple,Alder");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // it won't be added again
        inventoryRepository.addGuitar("123456", 1499.99, Builder.GIBSON, "Les Paul", Type.ELECTRIC, Wood.MAHOGANY, Wood.MAPLE);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            assertNull(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // the file will be empty
        inventoryRepository = new InventoryRepository();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            assertNull(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getGuitar() {
        InventoryRepository inventoryRepository = new InventoryRepository();
        inventoryRepository.addGuitar("123456", 999.99, Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.MAPLE, Wood.ALDER);
        inventoryRepository.addGuitar("234567", 999.99, Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.MAPLE, Wood.ALDER);
        assertNotNull(inventoryRepository.getGuitar("123456"));
        assertNotNull(inventoryRepository.getGuitar("234567"));
        assertNull(inventoryRepository.getGuitar("43079"));
        inventoryRepository = new InventoryRepository();
        assertNull(inventoryRepository.getGuitar("43079"));
        assertNull(inventoryRepository.getGuitar("234567"));
    }

    @Test
    void search() {
        InventoryRepository inventoryRepository = new InventoryRepository();
        inventoryRepository.addGuitar("123456", 999.99, Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.MAPLE, Wood.ALDER);
        inventoryRepository.addGuitar("234567", 999.99, Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.MAPLE, Wood.ALDER);
        inventoryRepository.addGuitar("345678", 899.99, Builder.GIBSON, "Les Paul", Type.ACOUSTIC, Wood.MAPLE, Wood.MAHOGANY);
        inventoryRepository.addGuitar("456789", 899.99, Builder.GIBSON, "SG", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAHOGANY);
        inventoryRepository.addGuitar("567890", 1199.99, Builder.COLLINGS, "RG", Type.ELECTRIC, Wood.CEDAR, Wood.MAHOGANY);
        inventoryRepository.addGuitar("678901", 699.99, Builder.GIBSON, "Soloist", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
        inventoryRepository.addGuitar("789101", 1299.99, Builder.PRS, "Custom 24", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        assertEquals(2, inventoryRepository.search(new Guitar(null, 999.99, null, null, null, null, null)).size());
        assertEquals(3, inventoryRepository.search(new Guitar(null, null, null, null, Type.ACOUSTIC, null, null)).size());
        assertEquals(2, inventoryRepository.search(new Guitar(null, null, null, null, Type.ACOUSTIC, Wood.MAHOGANY, null)).size());
        assertEquals(2, inventoryRepository.search(new Guitar(null, null, null, null, null, Wood.MAPLE, Wood.ALDER)).size());
        assertEquals(2, inventoryRepository.search(new Guitar(null, 899.99, Builder.GIBSON, null, null, null, null)).size());

    }
}