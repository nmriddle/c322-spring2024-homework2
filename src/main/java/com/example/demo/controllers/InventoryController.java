package com.example.demo.controllers;

import com.example.demo.model.Guitar;
import com.example.demo.repository.InventoryFileRepository;
import com.example.demo.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/guitars")
public class InventoryController {
    InventoryRepository inventoryRepository;
    InventoryFileRepository inventoryFileRepository;

    public InventoryController(InventoryRepository inventoryRepository, InventoryFileRepository inventoryFileRepository) {
        this.inventoryFileRepository = inventoryFileRepository;
        this.inventoryRepository = inventoryRepository;
    }

//    @GetMapping("/search")
//    public List<Guitar> search(
//            @RequestParam(required = false) String serialNumber,
//            @RequestParam(required = false) Double price,
//            @RequestParam(required = false) Builder builder,
//            @RequestParam(required = false) String model,
//            @RequestParam(required = false) Type type,
//            @RequestParam(required = false) Wood backWood,
//            @RequestParam(required = false) Wood topWood) {
//        Guitar searchGuitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
//        return inventoryFileRepository.search(searchGuitar);
////        return inventoryRepository.findById(searchGuitar);
//
//    }

    @GetMapping("/search")
    public List<Guitar> search(@RequestBody Guitar searchGuitar) {
        return inventoryRepository.search(
                searchGuitar.getSerialNumber(),
                searchGuitar.getBuilder(),
                searchGuitar.getPrice(),
                searchGuitar.getModel(),
                searchGuitar.getType(),
                searchGuitar.getBackWood(),
                searchGuitar.getTopWood()
        );
    }

    @PostMapping("/add")
    public void add(@RequestBody Guitar guitar) {
//        inventoryFileRepository.addGuitar(guitar.getSerialNumber(), guitar.getPrice(), guitar.getBuilder(), guitar.getModel(), guitar.getType(), guitar.getBackWood(), guitar.getTopWood());
//        inventoryRepository.save(guitar.getSerialNumber(), guitar.getPrice(), guitar.getBuilder(), guitar.getModel(), guitar.getType(), guitar.getBackWood(), guitar.getTopWood());
        inventoryRepository.save(guitar);
    }

    @GetMapping("/find")
    public Guitar find(@RequestParam String serialNumber) {
        return inventoryRepository.findBySerialNumber(serialNumber);
    }


}
