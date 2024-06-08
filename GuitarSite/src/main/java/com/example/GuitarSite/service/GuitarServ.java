package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.Guitar;
import com.example.GuitarSite.entity.enums.GuitarType;

import java.util.List;
/**
 * an interface for the GuitarServImp
 * here are implemented the methods that are used in the GuitarController
 */
public interface GuitarServ {
    Guitar updateGuitar(Guitar guitar);
    String deleteGuitar(int id);
    Guitar getGuitarByType(GuitarType type);
    Guitar getGuitarById(int id);
    List<Guitar> getGuitars();
    List<Guitar> saveGuitars(List<Guitar> guitars);
    Guitar saveGuitar(Guitar guitar);
}
