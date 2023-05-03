package com.example.bookaroom.bookaroom.campus;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;

import java.io.Serializable;
import java.util.Objects;

public class Sala implements Reservavel, Serializable, Comparable<Sala> {
    private Predio predio;
    private final String numero;
    private final Integer capacidade;

    // <editor-fold defaultstate="collapsed" desc="ItemReservavel">
    @Override
    public boolean contidoEm(Reserva reserva) {
        return reserva.getSala().equals(this);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Sala(String numero, Integer capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNumero() {
        return numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public Predio getPredio() {
        return predio;
    }

    protected void setPredio(Predio predio) {
        this.predio = predio;
    }

    // </editor-fold>

    @Override
    public String toString() {
        return this.getPredio().toString() + " " + this.numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sala sala)) return false;
        return getPredio().equals(sala.getPredio()) && getNumero().equals(sala.getNumero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPredio(), getNumero());
    }

    @Override
    public int compareTo(Sala o) {
        return toString().compareTo(o.toString());
    }
}
