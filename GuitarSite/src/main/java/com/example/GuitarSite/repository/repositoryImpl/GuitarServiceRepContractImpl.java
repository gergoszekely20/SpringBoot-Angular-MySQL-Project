package com.example.GuitarSite.repository.repositoryImpl;

import com.example.GuitarSite.entity.entity.GuitarService;
import com.example.GuitarSite.repository.GuitarServiceRep;
import com.example.GuitarSite.repository.repositoryContract.GuitarServiceRepContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuitarServiceRepContractImpl implements GuitarServiceRepContract {
    private GuitarServiceRep repository;

    @Autowired
    public GuitarServiceRepContractImpl(GuitarServiceRep repository) {
        this.repository = repository;
    }

    /**
     * this is a post http request saves a guitar given as input
     *
     * @param guitarService
     * @return
     */
    public GuitarService guitarServiceSave(GuitarService guitarService) {
        return repository.save(guitarService);
    }

    /**
     * this is a post http request saves all the guitars given as an input
     *
     * @param guitarServices
     * @return
     */
    public List<GuitarService> saveAllGuitarServices(List<GuitarService> guitarServices) {
        return repository.saveAll(guitarServices);
    }

    /**
     * this is a get http request that finds us all the guitars in the database
     *
     * @return
     */
    public List<GuitarService> getAllGuitarServices() {
        return repository.findAll();
    }

    /**
     * this is a get http request find the guitar by his id
     *
     * @param id
     * @return
     */
    public GuitarService guitarServiceById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * this is a get http request ve can find the Service by sorting after the guitar type
     *
     * @param type
     * @return
     */
    public GuitarService guitarServiceByType(String type) {
        return repository.findByType(type);
    }

    /**
     * this is a post delete request that deletes the Guitar service by a given id
     *
     * @param id
     * @return
     */
    public String guitarServiceDelete(int id) {
        repository.deleteById(id);
        return "GuitarService deleted successfully";
    }

    /**
     * this is put http request that hepls us to update the GuitarService by finding out the id and then updates the modified filds
     *
     * @param guitarService
     * @return
     */
    public GuitarService guitarServiceUpdate(GuitarService guitarService) {
        GuitarService existingService = repository.findById(guitarService.getIdService()).orElse(null);
        if (existingService != null) {
            existingService.setGuitarName(guitarService.getGuitarName());
            existingService.setGuitarProblem(guitarService.getGuitarProblem());
            existingService.setType(guitarService.getType());
            existingService.setIdUser(guitarService.getIdUser());
            return repository.save(existingService);
        } else {
            return null;
        }
    }
}
