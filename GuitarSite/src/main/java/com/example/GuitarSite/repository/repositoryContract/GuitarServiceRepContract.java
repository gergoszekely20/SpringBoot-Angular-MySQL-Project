package com.example.GuitarSite.repository.repositoryContract;

import com.example.GuitarSite.entity.entity.GuitarService;

import java.util.List;

public interface GuitarServiceRepContract {
    GuitarService guitarServiceUpdate(GuitarService guitarService);

    String guitarServiceDelete(int id);

    GuitarService guitarServiceByType(String type);

    GuitarService guitarServiceById(int id);

    List<GuitarService> getAllGuitarServices();

    List<GuitarService> saveAllGuitarServices(List<GuitarService> guitarServices);

    GuitarService guitarServiceSave(GuitarService guitarService);
}
