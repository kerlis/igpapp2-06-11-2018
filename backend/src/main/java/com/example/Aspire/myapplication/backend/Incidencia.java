package com.example.Aspire.myapplication.backend;

import java.io.Serializable;

/**
 * Created by Aspire on 25/12/2016.
 */

public class Incidencia implements Serializable{

    private String categoria;
    private String referencia;
    private String epicentro;
    private String profundidad;
    private String tiporeporte;
    private String lon;
    private String lat;
    private String horautc;
    private String magnitud;
    private String simulacro;
    private String fechautc;
    private String intenso;

    public String getIntenso() {
        return intenso;
    }

    public void setIntenso(String intenso) {
        this.intenso = intenso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEpicentro() {
        return epicentro;
    }

    public void setEpicentro(String epicentro) {
        this.epicentro = epicentro;
    }

    public String getFechautc() {
        return fechautc;
    }

    public void setFechautc(String fechautc) {
        this.fechautc = fechautc;
    }

    public String getHorautc() {
        return horautc;
    }

    public void setHorautc(String horautc) {
        this.horautc = horautc;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSimulacro() {
        return simulacro;
    }

    public void setSimulacro(String simulacro) {
        this.simulacro = simulacro;
    }

    public String getTiporeporte() {
        return tiporeporte;
    }

    public void setTiporeporte(String tiporeporte) {
        this.tiporeporte = tiporeporte;
    }

    public Incidencia() {
        this.tiporeporte ="x";
        this.simulacro="0";
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    public Incidencia(String simulacro, String magnitud, String categoria, String tiporeporte,
                      String lon, String profundidad, String fechautc, String referencia,
                      String horautc, String epicentro, String lat,String intenso) {
        this.categoria = categoria;
        this.epicentro = epicentro;
        this.fechautc = fechautc;
        this.horautc = horautc;
        this.lat = lat;
        this.lon = lon;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.referencia = referencia;
        this.simulacro = simulacro;
        this.tiporeporte = tiporeporte;
        this.intenso = intenso;
    }
}
