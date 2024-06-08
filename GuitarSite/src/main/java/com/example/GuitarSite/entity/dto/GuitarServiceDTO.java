package com.example.GuitarSite.entity.dto;

import com.example.GuitarSite.entity.enums.ServiceType;

import java.util.Date;

public class GuitarServiceDTO {
    private int idService;
    private int idUser;
    private String contactInfo;

    private String guitarName;
    private String type;
    private String guitarProblem;
    private Date date;
    private ServiceType serviceType;

    public GuitarServiceDTO(int idService, int idUser, String contactInfo, String guitarName, String type, String guitarProblem, Date date, ServiceType serviceType) {
        this.idService = idService;
        this.idUser = idUser;
        this.contactInfo = contactInfo;
        this.guitarName = guitarName;
        this.type = type;
        this.guitarProblem = guitarProblem;
        this.date = date;
        this.serviceType = serviceType;
    }

    public GuitarServiceDTO(){

    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
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

    public String getGuitarProblem() {
        return guitarProblem;
    }

    public void setGuitarProblem(String guitarProblem) {
        this.guitarProblem = guitarProblem;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
