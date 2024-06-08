package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.GuitarService;

import java.util.List;
/**
 * an interface for the GuitarServiceServImp
 * here are implemented the methods that are used in the GuitarServiceController
 */
public interface GuitarServiceServ {
    GuitarService updateGuitarService(GuitarService guitarService);
    String deleteGuitarService(int id);
    GuitarService getGuitarServiceByType(String type);
    GuitarService getGuitarServiceById(int id);
    List<GuitarService> getGuitarServices();
    List<GuitarService> saveGuitarServices(List<GuitarService> guitarServices);
    GuitarService saveGuitarService(GuitarService guitarService);

    GuitarService changeServiceStatus(int idService, String status);
}
