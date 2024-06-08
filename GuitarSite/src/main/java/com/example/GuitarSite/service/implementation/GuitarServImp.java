package com.example.GuitarSite.service.implementation;

import com.example.GuitarSite.entity.enums.GuitarType;
import com.example.GuitarSite.repository.repositoryContract.GuitarRepContract;
import com.example.GuitarSite.service.GuitarServ;
import com.example.GuitarSite.entity.entity.Guitar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * this is the class GuitarServImp where i implemented the get, post, put and delete endpoints
 */
@Service
public class GuitarServImp implements GuitarServ {
    /**
     * i called the GuitarRep to be able to call the function from the JpaRepository
     */
    private GuitarRepContract repository;

    @Autowired

    public GuitarServImp(GuitarRepContract repository) {
        this.repository = repository;
    }

    /**
     * this is a post http request what saves a guitar in the database
     *
     * @param guitar
     * @return the saved guitar
     */
    public Guitar saveGuitar(Guitar guitar) {
        return repository.guitarSave(guitar);

    }

    /**
     * this is a post http request what saves more guitars int the table from the data base
     *
     * @param guitars
     * @return
     */

    public List<Guitar> saveGuitars(List<Guitar> guitars) {
        return repository.saveAllGuitars(guitars);

    }

    /**
     * this is a get http request what finds all the guitars from the database
     *
     * @return
     */

    public List<Guitar> getGuitars() {
        return repository.getAllGuitars();
    }

    /**
     * this is a get http request what finds a gitar by his id
     *
     * @param id
     * @return
     */
    public Guitar getGuitarById(int id) {
        return repository.guitarById(id);
    }

    /**
     * this is a get http request what finds a guitar by his type
     *
     * @param type
     * @return
     */
    public Guitar getGuitarByType(GuitarType type) {
        return repository.guitarByType(type);
    }

    /**
     * this is a delete http request what deletes the guitar from the database and all his properties
     *
     * @param id
     * @return
     */
    public String deleteGuitar(int id) {
        repository.guitarDelete(id);
        return "deleted successfully";
    }

    /**
     * this is a put http request what updates the info from the guitar
     *
     * @param guitar
     * @return
     */
    public Guitar updateGuitar(Guitar guitar) {
        Guitar existigGuitar;
        existigGuitar = repository.guitarById(guitar.getIdGuitar());
        if (existigGuitar != null) {
            existigGuitar.setName(guitar.getName());
            existigGuitar.setType(guitar.getType());
            existigGuitar.setGuitarInfo(guitar.getGuitarInfo());
            existigGuitar.setPrice(guitar.getPrice());
            existigGuitar.setQuantity(guitar.getQuantity());
            existigGuitar.setImage(guitar.getImage());
            return repository.guitarSave(existigGuitar);
        } else {
            return null;
        }
    }
}
