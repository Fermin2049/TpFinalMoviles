package com.exploradordelugaresturisticos.entidades;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class LugarTuristico {
    private String nombre;

    private String descripcion;
    private String horarios;
    private String direccion;
    private int foto;
    private Double latitud;
    private Double longitud;

    public LugarTuristico(String nombre, Double lat, Double lon, String descripcion, String horarios, String direccion, int foto) {
        this.nombre = nombre;
        this.latitud = lat;
        this.longitud = lon;
        this.descripcion = descripcion;
        this.horarios = horarios;
        this.direccion = direccion;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLat() {
        return latitud;
    }

    public void setLat(Double lat) {
        this.latitud = lat;
    }

    public Double getLon() {
        return longitud;
    }

    public void setLon(Double lon) {
        this.longitud = lon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
