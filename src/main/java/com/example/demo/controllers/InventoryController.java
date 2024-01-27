package com.example.demo.controllers;

import com.example.demo.model.Guitar;
import com.example.demo.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/guitars")
public class InventoryController {
    InventoryRepository inventoryRepository;

    public InventoryController() {
        inventoryRepository = new InventoryRepository();
    }

    @GetMapping("/search")
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

    @PostMapping("/add")
    public void add(@RequestBody Guitar guitar) {
        inventoryRepository.addGuitar(guitar.getSerialNumber(), guitar.getPrice(), guitar.getBuilder(), guitar.getModel(), guitar.getType(), guitar.getBackWood(), guitar.getTopWood());
    }

    @GetMapping("/find")
    public Guitar find(@RequestParam String serialNumber) {
        return inventoryRepository.getGuitar(serialNumber);
    }
}
