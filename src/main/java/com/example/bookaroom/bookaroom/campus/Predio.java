package com.example.bookaroom.bookaroom.campus;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Predio implements Serializable {
    private final String nome;
    private Campus campus;
    private List<Sala> salas;

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Predio(String nome, List<Sala> salas) {
        this.nome = nome;
        setSalas(salas);
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public Campus getCampus() {
        return campus;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    protected void setCampus(Campus campus) {
        this.campus = campus;
    }

    protected void setSalas(List<Sala> salas) {
        salas.forEach(sala -> sala.setPredio(this));
        this.salas = salas;
    }
    //    </editor-fold>

    @Override
    public String toString() {
        return this.campus.toString() + " " + this.getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predio predio)) return false;
        return getNome().equals(predio.getNome()) && getCampus().equals(predio.getCampus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getCampus());
    }

}
