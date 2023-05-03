package com.example.bookaroom.bookaroom.periodo;

import java.time.LocalDate;
import java.util.Iterator;

public class PeriodoIterator implements Iterator<Periodo> {
    LocalDate dataAlocacao;
    LocalDate dataExpiracao;
    LocalDate dataIter;
    Horario horario;
    int frequenciaEmDias;
    boolean end;

    public PeriodoIterator(LocalDate dataAlocacao, LocalDate dataExpiracao, Horario horario, Integer frequenciaEmDias) {
        this.dataAlocacao = dataAlocacao;
        this.dataExpiracao = dataExpiracao;
        this.dataIter = LocalDate.from(dataAlocacao);
        this.horario = horario;
        this.frequenciaEmDias = frequenciaEmDias;
        this.end = dataAlocacao.isAfter(dataExpiracao);
    }

    @Override
    public boolean hasNext() {
        return !this.end;
    }

    @Override
    public Periodo next() {
        Periodo periodo = new Periodo(LocalDate.from(dataIter), horario.inicio, horario.fim);
        dataIter = dataIter.plusDays(frequenciaEmDias);
        this.end = frequenciaEmDias == 0 || dataIter.isAfter(dataExpiracao);

        return periodo;
    }
}
