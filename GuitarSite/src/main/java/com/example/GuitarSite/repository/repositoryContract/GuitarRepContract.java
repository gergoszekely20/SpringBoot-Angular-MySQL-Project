package com.example.GuitarSite.repository.repositoryContract;

import com.example.GuitarSite.entity.entity.Guitar;
import com.example.GuitarSite.entity.enums.GuitarType;

import java.util.List;

public interface GuitarRepContract {
    Guitar guitarUpdate(Guitar guitar);

    String guitarDelete(int id);

    Guitar guitarByType(GuitarType type);

    Guitar guitarById(int id);

    List<Guitar> getAllGuitars();

    List<Guitar> saveAllGuitars(List<Guitar> guitars);

    Guitar guitarSave(Guitar guitar);
}
