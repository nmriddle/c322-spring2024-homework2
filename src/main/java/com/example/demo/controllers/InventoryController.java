package com.example.demo.controllers;

import com.example.demo.model.Guitar;
import com.example.demo.repository.InventoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class InventoryController {
    InventoryRepository inventoryRepository;

    public InventoryController() {
        inventoryRepository = new InventoryRepository();
    }

    @GetMapping("/search?serialNumber={serialNumber}")
    public List<Guitar> search(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String builder,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String backWood,
            @RequestParam(required = false) String topWood) {
        Guitar searchGuitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        return inventoryRepository.search(searchGuitar);
    }

    @PostMapping
    public void add(@RequestParam String serialNumber,
                    @RequestParam double price,
                    @RequestParam String builder,
                    @RequestParam String model,
                    @RequestParam String type,
                    @RequestParam String backWood,
                    @RequestParam String topWood){
        inventoryRepository.addGuitar(serialNumber,price,builder,model,type,backWood,topWood);
    }

    @GetMapping
    public Guitar find(@RequestParam String serialNumber){
        return inventoryRepository.getGuitar(serialNumber);
    }
}
