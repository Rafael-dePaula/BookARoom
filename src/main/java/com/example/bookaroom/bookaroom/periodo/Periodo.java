package com.example.bookaroom.bookaroom.periodo;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Periodo implements Reservavel, Serializable {
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public LocalDateTime inicio;
    public LocalDateTime fim;

    public Periodo(LocalDateTime Inicio, LocalDateTime Fim) {
        this.inicio = Inicio;
        this.fim = Fim;
    }

    public Periodo(String inicio, String fim) {
        this(LocalDateTime.parse(inicio, FORMATO_DATA_HORA), LocalDateTime.parse(fim, FORMATO_DATA_HORA));
    }

    public Periodo(String data, String horaInicio, String horaFim) {
        this(data + " " + horaInicio,data + " " + horaFim);
    }

    public Periodo(String dataInicio, String horaInicio, String dataFim, String horaFim) {
        this(dataInicio + " " + horaInicio, dataFim + " " + horaFim);
    }

    public Periodo(LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        this(LocalDateTime.of(data, horaInicio), LocalDateTime.of(data, horaFim));
    }

    public static Periodo of(LocalDate data, Horario horario) {
        return new Periodo(data, horario.inicio, horario.fim);
    }

    public boolean overlaps(Periodo periodo) {
        return periodo.inicio.isBefore(this.fim)
                && periodo.fim.isAfter(this.inicio);
    }

    @Override
    public boolean contidoEm(Reserva reserva) {
        return reserva.hasOverlap(this);
    }

    @Override
    public String toString() {
        if(inicio.toLocalDate() == fim.toLocalDate()) {
            return inicio.format(FORMATO_DATA_HORA) + " - " + fim.format(FORMATO_HORA);
        }
        return inicio.format(FORMATO_DATA_HORA) + " - " + fim.format(FORMATO_DATA_HORA);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Periodo periodo)) return false;
        return inicio.equals(periodo.inicio) && fim.equals(periodo.fim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio, fim);
    }
}
