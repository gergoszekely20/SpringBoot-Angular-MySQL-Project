package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.GuitarService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * this is the interface where the GuitarServiceRep extends the JpaRepository
 * where we can find the repository functions which communicates with the database
 */
public interface GuitarServiceRep extends JpaRepository<GuitarService,Integer> {
    GuitarService findByType(String type);
}
