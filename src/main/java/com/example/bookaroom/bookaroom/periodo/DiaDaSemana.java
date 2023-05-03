package com.example.bookaroom.bookaroom.periodo;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

public enum DiaDaSemana implements Reservavel, TemporalAdjuster, Serializable {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;

    private static final DiaDaSemana[] ENUMS = DiaDaSemana.values();

    public DayOfWeek toDayOfWeek() {
        return DayOfWeek.of(ordinal() + 1);
    }

    public static DiaDaSemana of(DayOfWeek dayOfWeek) {
        return ENUMS[dayOfWeek.getValue() - 1];
    }

    public static List<DiaDaSemana> toList() {
        return List.of(DiaDaSemana.values());
    }

    @Override
    public boolean contidoEm(Reserva reserva) {
        return equals(reserva.getDiaSemana());
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return toDayOfWeek().adjustInto(temporal);
    }
}
