package com.example.GuitarSite.service.implementation;

import com.example.GuitarSite.entity.enums.ServiceType;
import com.example.GuitarSite.repository.repositoryContract.GuitarServiceRepContract;
import com.example.GuitarSite.service.GuitarServiceServ;
import com.example.GuitarSite.entity.entity.GuitarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * this is the class GuitarServiceServImp where I implemented the get, post, put and delete endpoints
 */
@Service
public class GuitarServiceServImp implements GuitarServiceServ {
    private GuitarServiceRepContract repository;

    @Autowired

    public GuitarServiceServImp(GuitarServiceRepContract repository) {
        this.repository = repository;
    }

    /**
     * this is a post http request saves a guitar given as input
     *
     * @param guitarService
     * @return
     */
    public GuitarService saveGuitarService(GuitarService guitarService) {
        return repository.guitarServiceSave(guitarService);
    }

    /**
     * this is a post http request saves all the guitars given as an input
     *
     * @param guitarServices
     * @return
     */
    public List<GuitarService> saveGuitarServices(List<GuitarService> guitarServices) {
        return repository.saveAllGuitarServices(guitarServices);
    }

    /**
     * this is a get http request that finds us all the guitars in the database
     *
     * @return
     */
    public List<GuitarService> getGuitarServices() {
        return repository.getAllGuitarServices();
    }

    /**
     * this is a get http request find the guitar by his id
     *
     * @param id
     * @return
     */
    public GuitarService getGuitarServiceById(int id) {
        return repository.guitarServiceById(id);
    }

    /**
     * this is a get http request ve can find the Service by sorting after the guitar type
     *
     * @param type
     * @return
     */
    public GuitarService getGuitarServiceByType(String type) {
        return repository.guitarServiceByType(type);
    }

    /**
     * this is a post delete request that deletes the Guitar service by a given id
     *
     * @param id
     * @return
     */
    public String deleteGuitarService(int id) {
        repository.guitarServiceDelete(id);
        return "GuitarService deleted successfully";
    }

    /**
     * this is put http request that hepls us to update the GuitarService by finding out the id and then updates the modified filds
     *
     * @param guitarService
     * @return
     */
    public GuitarService updateGuitarService(GuitarService guitarService) {
        GuitarService existingService = repository.guitarServiceById(guitarService.getIdService());
        if (existingService != null) {
            existingService.setGuitarName(guitarService.getGuitarName());
            existingService.setGuitarProblem(guitarService.getGuitarProblem());
            existingService.setType(guitarService.getType());
            existingService.setIdUser(guitarService.getIdUser());
            return repository.guitarServiceSave(existingService);
        } else {
            return null;
        }
    }

    public GuitarService changeServiceStatus(int idService, String status) {
        GuitarService guitarService = repository.guitarServiceById(idService);

        if (guitarService != null) {
            if (Objects.equals(status, "FINISHED")) {
                guitarService.setServiceType(ServiceType.FINISHED);
            }
            return repository.guitarServiceSave(guitarService);
        }
        return null;
    }

}
