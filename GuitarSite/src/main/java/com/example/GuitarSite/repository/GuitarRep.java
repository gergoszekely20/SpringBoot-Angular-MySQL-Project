package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.Guitar;
import com.example.GuitarSite.entity.enums.GuitarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * this is the interface where the GuitarRep extends the JpaRepository
 * where we can find the repository functions which communicates with the database
 */
public interface GuitarRep extends JpaRepository<Guitar, Integer> {
    Guitar findByType(GuitarType type);

    @Query("SELECT g FROM Guitar g WHERE g.idGuitar = :id AND g.quantity > 0")
    Optional<Guitar> findByIdAndQuantityGreaterThanZero(@Param("id") Integer id);

    @Query("SELECT g FROM Guitar g WHERE g.quantity > 0")
    List<Guitar> findAllWithQuantityGreaterThanZero();
}
