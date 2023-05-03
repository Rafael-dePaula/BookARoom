package com.example.bookaroom.bookaroom.periodo;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;

import java.io.Serializable;
import java.time.LocalTime;

public class Horario implements Reservavel, Serializable {
    public LocalTime inicio;
    public LocalTime fim;

    public Horario(LocalTime inicio, LocalTime fim) {
        this.inicio = inicio;
        this.fim = fim;

        if(!fim.isAfter(inicio)) {
            throw new IllegalArgumentException("Horario de Inicio precisa acontecer antes do Horario de Termino");
        }
    }

    public Horario(String inicio, String fim) {
        this(LocalTime.parse(inicio, Periodo.FORMATO_HORA), LocalTime.parse(fim, Periodo.FORMATO_HORA));
    }

    @Override
    public String toString() {
        return inicio.format(Periodo.FORMATO_HORA) +
                " - " + fim.format(Periodo.FORMATO_HORA);
    }

    public boolean overlaps(Horario horario) {
        return this.inicio.isBefore(horario.fim)
                && this.fim.isAfter(horario.inicio);
    }

    @Override
    public boolean contidoEm(Reserva reserva) {
        return overlaps(reserva.getHorario());
    }
}
