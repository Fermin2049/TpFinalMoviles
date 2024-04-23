package com.exploradordelugaresturisticos.entidades;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class LugarTuristico {
    private LatLng coordenadas;
    private  String nombre;
    private  String descripcion;
    private List<Integer> fotos;

    public LugarTuristico(LatLng coordenadas, String nombre, String descripcion, List<Integer> fotos) {
        this.coordenadas = coordenadas;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos = fotos;
    }

    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Integer> getFotos() {
        return fotos;
    }

    public void setFotos(List<Integer> fotos) {
        this.fotos = fotos;
    }
}
