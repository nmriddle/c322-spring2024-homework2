package com.example.demo.repository;

import com.example.demo.model.Builder;
import com.example.demo.model.Guitar;
import com.example.demo.model.Type;
import com.example.demo.model.Wood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Guitar, String> {
    Guitar findBySerialNumber(String serialNumber);

    @Query("SELECT g FROM Guitar g " +
            "WHERE (:serialNumber IS NULL OR g.serialNumber = :serialNumber) " +
            "AND (:builder IS NULL OR g.builder = :builder) " +
            "AND (:price IS NULL OR g.price = :price) " +
            "AND (:model IS NULL OR g.model = :model) " +
            "AND (:type IS NULL OR g.type = :type) " +
            "AND (:backWood IS NULL OR g.backWood = :backWood) " +
            "AND (:topWood IS NULL OR g.topWood = :topWood)")
    List<Guitar> search(@Param("serialNumber") String serialNumber,
                        @Param("builder") Builder builder,
                        @Param("price") Double price,
                        @Param("model") String model,
                        @Param("type") Type type,
                        @Param("backWood") Wood backWood,
                        @Param("topWood") Wood topWood);


}
