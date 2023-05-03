package com.example.bookaroom.bookaroom.periodo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.function.Consumer;

public class AlocacoesHorario implements Serializable, Iterable<Periodo> {
        LocalDate dataAlocacao;
        LocalDate dataExpiracao;
        Horario horario;
        int frequenciaEmDias;

    public LocalDate dataAlocacao() {
        return dataAlocacao;
    }

    public LocalDate dataExpiracao() {
        return dataExpiracao;
    }

    public Horario horario() {
        return horario;
    }

    public AlocacoesHorario(LocalDate dataAlocacao, LocalDate dataExpiracao, Horario horario, int frequenciaEmDias) {
        this.dataAlocacao = dataAlocacao;
        this.dataExpiracao = dataExpiracao;
        this.horario = horario;
        this.frequenciaEmDias = frequenciaEmDias;
    }

    static public AlocacoesHorario semanal(LocalDate dataAlocacao, LocalDate dataExpiracao, Horario horario) {
        return new AlocacoesHorario(dataAlocacao, dataExpiracao, horario, 7);
    }

    static public AlocacoesHorario semanal(Semestre semestre, DiaDaSemana diaDaSemana, Horario horario) {
        LocalDate semestreInicio = semestre.inicio.toLocalDate();
        LocalDate semestreFim = semestre.fim.toLocalDate();

        LocalDate dataAlocacao = semestreInicio.with(diaDaSemana);
        if(dataAlocacao.isBefore(semestreInicio)) {
            dataAlocacao = dataAlocacao.plusDays(7);
        }

        return semanal(dataAlocacao, semestreFim, horario);
    }

    static public AlocacoesHorario unico(LocalDate dataAlocacao, Horario horario) {
        return new AlocacoesHorario(dataAlocacao, dataAlocacao, horario, 0);
    }

    public Periodo getFirstOccurrenceAfter(LocalDate targetDate) {
        if(targetDate.isAfter(dataExpiracao)) return null;
        if(targetDate.isEqual(dataAlocacao)) return new Periodo(dataAlocacao, horario.inicio, horario.fim);
        if(frequenciaEmDias == 0) return null;

        long differenceToNext = ChronoUnit.DAYS.between(dataAlocacao, targetDate) % frequenciaEmDias;
        long daysToPlus = differenceToNext == 0L ? 0L : frequenciaEmDias - differenceToNext;

        return new Periodo(targetDate.plusDays(daysToPlus), horario.inicio, horario.fim);
    }

    @Override
    public Iterator<Periodo> iterator() {
        return new PeriodoIterator(dataAlocacao, dataExpiracao, horario, frequenciaEmDias);
    }

    @Override
    public void forEach(Consumer<? super Periodo> action) {
        Iterable.super.forEach(action);
    }

    public Periodo getPeriodoTotal() {
        return new Periodo(LocalDateTime.of(dataAlocacao, horario.inicio), LocalDateTime.of(dataExpiracao, horario.fim));
    }

}
