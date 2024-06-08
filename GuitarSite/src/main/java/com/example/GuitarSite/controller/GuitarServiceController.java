package com.example.GuitarSite.controller;


import com.example.GuitarSite.entity.dto.GuitarServiceDTO;
import com.example.GuitarSite.entity.enums.ServiceType;
import com.example.GuitarSite.service.GuitarServiceServ;
import com.example.GuitarSite.entity.entity.GuitarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * this is the GuitarServiceController class that makes the connection with the interface and here we can see he http requests mapped
 * also here we can see the endpoints for each methode that was implemented in the GuitarServiceServImp
 */
@RestController
public class GuitarServiceController {
    @Autowired
    private GuitarServiceServ guitarServiceService;


    /**
     * Controller handling guitar service-related HTTP requests.
     * Adds a new guitar service via HTTP POST request.
     */
    @PostMapping("/addGuitarService")
    public GuitarServiceDTO addGuitarService(@RequestBody GuitarServiceDTO guitarServiceDTO) {

        GuitarService guitarService = new GuitarService();
        guitarService.setIdService(guitarServiceDTO.getIdService());
        guitarService.setIdUser(guitarServiceDTO.getIdUser());
        guitarService.setContactInfo(guitarServiceDTO.getContactInfo());
        guitarService.setGuitarName(guitarServiceDTO.getGuitarName());
        guitarService.setType(guitarServiceDTO.getType());
        guitarService.setGuitarProblem(guitarServiceDTO.getGuitarProblem());
        guitarService.setDate(new Date());
        guitarService.setServiceType(ServiceType.INPROGRESS);

        // Save the entity
        GuitarService savedGuitarService = guitarServiceService.saveGuitarService(guitarService);

        // Convert entity back to DTO
        GuitarServiceDTO responseDTO = new GuitarServiceDTO(
                savedGuitarService.getIdService(),
                savedGuitarService.getIdUser(),
                savedGuitarService.getContactInfo(),
                savedGuitarService.getGuitarName(),
                savedGuitarService.getType(),
                savedGuitarService.getGuitarProblem(),
                savedGuitarService.getDate(),
                savedGuitarService.getServiceType()
        );

        return responseDTO;
    }


    /**
     * Controller handling bulk guitar service addition via HTTP POST request.
     */
    @PostMapping("/addGuitarServices")
    public List<GuitarService> addGuitarServices(@RequestBody List<GuitarService> guitars) {
        return guitarServiceService.saveGuitarServices(guitars);
    }

    /**
     * Retrieves all guitar services via HTTP GET request.
     */
    @GetMapping("/showGuitarServices")
    public List<GuitarServiceDTO> findGuitarServices() {
        List<GuitarService> guitarServiceList = guitarServiceService.getGuitarServices();
        List<GuitarServiceDTO> guitarServiceDTOS = new ArrayList<>();

        for (GuitarService guitarService : guitarServiceList) {

            GuitarServiceDTO guitarServiceDTO = new GuitarServiceDTO(guitarService.getIdService(),guitarService.getIdUser(),guitarService.getContactInfo(),guitarService.getGuitarName(),guitarService.getType(),guitarService.getGuitarProblem(),guitarService.getDate(),guitarService.getServiceType());


                    guitarServiceDTOS.add(guitarServiceDTO);
        }
        return guitarServiceDTOS;
    }

    /**
     * Retrieves a guitar service by ID via HTTP GET request.
     */
    @GetMapping("/guitarServiceById/{id}")
    public GuitarService findGuitarServiceById(@PathVariable int id) {
        return guitarServiceService.getGuitarServiceById(id);
    }

    /**
     * Retrieves a guitar service by type via HTTP GET request.
     */
    @GetMapping("/guitarServiceByType/{type}")
    public GuitarService findServiceByGuitarType(@PathVariable String type) {
        return guitarServiceService.getGuitarServiceByType(type);
    }

    /**
     * Updates a guitar service via HTTP PUT request.
     */
    @PutMapping("/guitarServiceUpdate")
    public GuitarService updateGuitarService(@RequestBody GuitarService guitar) {
        return guitarServiceService.updateGuitarService(guitar);
    }

    /**
     * Deletes a guitar service by ID via HTTP DELETE request.
     */
    @DeleteMapping("/guitarServiceDelete/{id}")
    public String deleteGuitarService(@PathVariable int id) {
        return guitarServiceService.deleteGuitarService(id);
    }

    @GetMapping("/changeServiceStatus/{idService}/{status}")
    public ResponseEntity changeORderStatus(@PathVariable int idService, @PathVariable String status ){
        GuitarService guitarServiceDTO = guitarServiceService.changeServiceStatus(idService,status);
        if(guitarServiceDTO == null){
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(guitarServiceDTO);
    }
}
