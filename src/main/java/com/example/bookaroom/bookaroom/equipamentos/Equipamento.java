package com.example.bookaroom.bookaroom.equipamentos;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;

import java.io.Serializable;
import java.util.Objects;

public abstract class Equipamento implements Reservavel, Serializable {
    final String nome;
    String patrimonio = null;

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    protected Equipamento(String nome) {
        this.nome = nome;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ItemReservavel">
    @Override
    public boolean contidoEm(Reserva reserva) {
        return reserva.getEquipamentos().contains(this);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ToString/Equals/HashCode">
    @Override
    public String toString() {
        return getNome() + " - " + getPatrimonio();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipamento that)) return false;
        return getNome().equals(that.getNome()) && getPatrimonio().equals(that.getPatrimonio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatrimonio());
    }

    // </editor-fold>
}
