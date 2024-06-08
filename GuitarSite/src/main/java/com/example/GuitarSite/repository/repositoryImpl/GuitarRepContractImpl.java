package com.example.GuitarSite.repository.repositoryImpl;

import com.example.GuitarSite.entity.entity.Guitar;
import com.example.GuitarSite.entity.enums.GuitarType;
import com.example.GuitarSite.repository.GuitarRep;
import com.example.GuitarSite.repository.repositoryContract.GuitarRepContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuitarRepContractImpl implements GuitarRepContract {
    private GuitarRep repository;

    @Autowired
    public GuitarRepContractImpl(GuitarRep repository){
        this.repository = repository;
    }

    /**
     * this is a post http request what saves a guitar in the database
     * @param guitar
     * @return the saved guitar
     */
    public Guitar guitarSave(Guitar guitar) {
        return repository.save(guitar);

    }

    /**
     * this is a post http request what saves more guitars int the table from the data base
     * @param guitars
     * @return
     */

    public List<Guitar> saveAllGuitars(List<Guitar> guitars) {
        return repository.saveAll(guitars);

    }

    /**
     * this is a get http request what finds all the guitars from the database
     * @return
     */

    public List<Guitar> getAllGuitars() {
        return repository.findAllWithQuantityGreaterThanZero();
    }

    /**
     * this is a get http request what finds a gitar by his id
     * @param id
     * @return
     */
    public Guitar guitarById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * this is a get http request what finds a guitar by his type
     * @param type
     * @return
     */
    public Guitar guitarByType(GuitarType type) {
        return repository.findByType(type);
    }

    /**
     * this is a delete http request what deletes the guitar from the database and all his properties
     * @param id
     * @return
     */
    public String guitarDelete(int id) {
        repository.deleteById(id);
        return "deleted successfully";
    }

    /**
     * this is a put http request what updates the info from the guitar
     * @param guitar
     * @return
     */
    public Guitar guitarUpdate(Guitar guitar) {
        Guitar existigGuitar;
        existigGuitar = repository.findById(guitar.getIdGuitar()).orElse(null);
        if (existigGuitar != null) {
            existigGuitar.setName(guitar.getName());
            existigGuitar.setType(guitar.getType());
            existigGuitar.setGuitarInfo(guitar.getGuitarInfo());
            existigGuitar.setPrice(guitar.getPrice());
            existigGuitar.setQuantity(guitar.getQuantity());
            existigGuitar.setImage(guitar.getImage());
            return repository.save(existigGuitar);
        }else {
            return  null;
        }
    }
}
