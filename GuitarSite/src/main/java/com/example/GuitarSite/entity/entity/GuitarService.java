package com.example.GuitarSite.entity.entity;

import com.example.GuitarSite.entity.enums.ServiceType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

/**
 * this class was made for the guitar service, this class helps us to create objects for guitarServices
 */
@Entity
@Table(name = "GuitarService")
public class GuitarService {
    @Id
    @GeneratedValue
    private int idService;
    private int idUser;
    private String contactInfo;

    private String guitarProblem;
    private String guitarName;
    private String type;
    private Date date;

    private ServiceType serviceType;

    public GuitarService(int idService, int idUser, String contactInfo, String guitarProblem, String guitarName, String type, Date date, ServiceType serviceType) {
        this.idService = idService;
        this.idUser = idUser;
        this.contactInfo = contactInfo;
        this.guitarProblem = guitarProblem;
        this.guitarName = guitarName;
        this.type = type;
        this.date = date;
        this.serviceType = serviceType;
    }

    /**
     * this is the constructor for this class, which creates the GuitarService object
     *
     * @param idService     this is the primary key in the database it is generated automatically
     * @param idUser        after the user is logged in we will save his id to know which user needed service
     * @param contactInfo
     * @param guitarProblem specifys what the service has to do with the guitar later on
     * @param guitarName    the guitar brand
     * @param type          the guitar type
     */

    public GuitarService(int idService, int idUser, String contactInfo, String guitarProblem, String guitarName, String type) {
        this.idService = idService;
        this.idUser = idUser;
        this.contactInfo = contactInfo;
        this.guitarProblem = guitarProblem;
        this.guitarName = guitarName;
        this.type = type;
    }

    public GuitarService() {

    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }


    public String getGuitarProblem() {
        return guitarProblem;
    }

    public void setGuitarProblem(String guitarProblem) {
        this.guitarProblem = guitarProblem;
    }

    public String getGuitarName() {
        return guitarName;
    }

    public void setGuitarName(String guitarName) {
        this.guitarName = guitarName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
