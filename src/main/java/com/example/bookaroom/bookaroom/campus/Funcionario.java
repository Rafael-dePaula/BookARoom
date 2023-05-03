package com.example.bookaroom.bookaroom.campus;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;

import java.io.Serializable;
import java.util.Objects;

public final class Funcionario implements Reservavel, Serializable {
    private final String nome;
    private final String cargo;
    private final String ramal;

    public Funcionario(String nome, String cargo, String ramal) {
        this.nome = nome;
        this.cargo = cargo;
        this.ramal = ramal;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ItemReservavel">
    @Override
    public boolean contidoEm(Reserva reserva) {
        return reserva.getFuncionario().equals(this);
    }
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario that)) return false;
        return nome().equals(that.nome()) && cargo().equals(that.cargo()) && ramal().equals(that.ramal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome(), cargo(), ramal());
    }

    @Override
    public String toString() {
        return nome;
    }

    public String nome() {
        return nome;
    }

    public String cargo() {
        return cargo;
    }

    public String ramal() {
        return ramal;
    }

}
