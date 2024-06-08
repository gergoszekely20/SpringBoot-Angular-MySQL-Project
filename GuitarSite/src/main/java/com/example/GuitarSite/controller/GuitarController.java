package com.example.GuitarSite.controller;

import com.example.GuitarSite.entity.dto.GuitarDTO;
import com.example.GuitarSite.entity.enums.GuitarType;
import com.example.GuitarSite.service.GuitarServ;
import com.example.GuitarSite.entity.entity.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * this is the GuitarController class that makes the connection with the interface and here we can see he http requests mapped
 * also here we can see the endpoints for each methode that was implemented in the GuitarServImp
 */

@RestController
public class GuitarController {
    @Autowired
    private GuitarServ guitarService;
    /**
     * Controller handling guitar-related HTTP requests.
     * Adds a new guitar via HTTP POST request.
     */

    private String uploadDir = "D:/Egyetem/PS/Proiecte/GuitarSite/src/main/resources/Images";

    @PostMapping("/addGuitar")
    public ResponseEntity addGuitar(
            @RequestParam("name") String name,
            @RequestParam("type") GuitarType type,
            @RequestParam("price") float price,
            @RequestParam("guitarInfo") String guitarInfo,
            @RequestParam("quantity") int quantity,
            @RequestParam("image") MultipartFile image) {
        try {
            String fileName = image.getOriginalFilename();
            Path path = Paths.get(uploadDir + File.separator + fileName);
            Files.write(path, image.getBytes());

            Guitar guitar = new Guitar();
            guitar.setName(name);
            guitar.setType(type);
            guitar.setPrice(price);
            guitar.setGuitarInfo(guitarInfo);
            guitar.setQuantity(quantity);
            guitar.setImage(fileName);

            Guitar savedGuitar = guitarService.saveGuitar(guitar);
            return ResponseEntity.ok(savedGuitar);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Image upload failed: " + e.getMessage());
        }
    }

    /**
     * Controller handling bulk guitar addition via HTTP POST request.
     */
    @PostMapping("/addGuitars")
    public List<Guitar> addGuitars(@RequestBody List<Guitar> guitars) {
        return guitarService.saveGuitars(guitars);
    }

    /**
     * Retrieves all guitars via HTTP GET request.
     */
    @GetMapping("/showGuitars")
    public List<GuitarDTO> findGuitars() throws IOException {
        List<Guitar> guitars = guitarService.getGuitars();
        List<GuitarDTO> guitarDTOs = new ArrayList<>();
        for (Guitar guitar : guitars) {
            byte[] imageBytes = Files.readAllBytes(Path.of(uploadDir + "/" + guitar.getImage()));

            GuitarDTO guitarDTO = new GuitarDTO(guitar.getIdGuitar(), guitar.getName(), guitar.getType(), guitar.getPrice(), guitar.getGuitarInfo(), guitar.getQuantity(), imageBytes);

            guitarDTOs.add(guitarDTO);
        }
        return guitarDTOs;
    }


    /**
     * Retrieves a guitar by ID via HTTP GET request.
     */
    @GetMapping("/guitarById/{id}")
    public ResponseEntity<GuitarDTO> findGuitarById(@PathVariable int id) {
        Guitar guitar = guitarService.getGuitarById(id);
        if (guitar != null) {
            byte[] imageBytes;
            try {
                imageBytes = Files.readAllBytes(Paths.get(uploadDir + "/" + guitar.getImage()));
                GuitarDTO guitarDTO = new GuitarDTO(
                        guitar.getIdGuitar(),
                        guitar.getName(),
                        guitar.getType(),
                        guitar.getPrice(),
                        guitar.getGuitarInfo(),
                        guitar.getQuantity(),
                        imageBytes
                );
                return ResponseEntity.ok(guitarDTO);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a guitar by type via HTTP GET request.
     */
    @GetMapping("/guitarByType/{type}")
    public Guitar findGuitaryName(@PathVariable GuitarType type) {
        return guitarService.getGuitarByType(type);
    }

    /**
     * Updates a guitar via HTTP PUT request.
     */
    @PutMapping("/guitarUpdate")
    public ResponseEntity<Guitar> updateGuitar(@RequestBody GuitarDTO guitarDTO) {
        Guitar existingGuitar = guitarService.getGuitarById(guitarDTO.getIdGuitar());
        if (existingGuitar != null) {
            existingGuitar.setName(guitarDTO.getName());
            existingGuitar.setType(guitarDTO.getType());
            existingGuitar.setPrice(guitarDTO.getPrice());
            existingGuitar.setGuitarInfo(guitarDTO.getGuitarInfo());
            existingGuitar.setQuantity(guitarDTO.getQuantity());
            if (guitarDTO.getImage() != null) {
                try {
                    String fileName = existingGuitar.getImage();
                    Path path = Paths.get(uploadDir + File.separator + fileName);
                    Files.write(path, guitarDTO.getImage());
                } catch (IOException e) {
                    return ResponseEntity.status(500).body(null);
                }
            }
            Guitar updatedGuitar = guitarService.updateGuitar(existingGuitar);
            return ResponseEntity.ok(updatedGuitar);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Deletes a guitar by ID via HTTP DELETE request.
     */
    @DeleteMapping("/guitarDelete/{id}")
    public ResponseEntity<Object> deleteGuitar(@PathVariable int id) {
        String deleted = guitarService.deleteGuitar(id);
        if (deleted == "deleted successfully") {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
