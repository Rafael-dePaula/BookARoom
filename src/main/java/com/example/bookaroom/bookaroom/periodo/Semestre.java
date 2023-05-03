package com.example.bookaroom.bookaroom.periodo;

import com.example.bookaroom.bookaroom.reserva.Aula;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Semestre extends Periodo implements Serializable {
    protected String id = null;

    public static final DateTimeFormatter FORMATO_MES_ANO = DateTimeFormatter.ofPattern("MM/yyyy");

    private final List<Aula> aulas;

    public Semestre(LocalDate Inicio, LocalDate Fim, List<Aula> aulas) {
        super(LocalDateTime.of(Inicio, LocalTime.MIN),
                LocalDateTime.of(Fim, LocalTime.MAX));
        this.aulas = aulas;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void addAula(Aula aula) {
        aulas.add(aula);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Semestre semestre)) return false;
        return toString().equals(semestre.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        return inicio.format(FORMATO_MES_ANO) + " - " + fim.format(FORMATO_MES_ANO);
    }
}
