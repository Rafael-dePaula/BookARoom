package com.example.bookaroom.bookaroom.reserva;

import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.campus.Funcionario;
import com.example.bookaroom.bookaroom.periodo.*;
import com.example.bookaroom.bookaroom.campus.Sala;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.Objects;


public abstract class Reserva implements Serializable {
    AlocacoesHorario horariosAlocados;
    final String assunto;
    Boolean ativa;
    final Sala sala;
    final Funcionario funcionario;
    final List<Equipamento> equipamentos;


    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Reserva(AlocacoesHorario horariosAlocados, Funcionario funcionario, Sala sala, String assunto, List<Equipamento> equipamentos) {
        this.horariosAlocados = horariosAlocados;
        this.funcionario = funcionario;
        this.sala = sala;
        this.assunto = assunto;
        this.equipamentos = equipamentos;
        this.ativa = true;
    }
    //  </editor-fold>

    abstract public TipoReserva getTipoReserva();

    public int getPrioridade() {
        return getTipoReserva().prioridade;
    }

    public AlocacoesHorario periodos() {
        return horariosAlocados;
    }

    public boolean hasOverlap(Periodo p) {
        Periodo nextP = periodos().getFirstOccurrenceAfter(p.inicio.toLocalDate());
        if(nextP == null) return false;

        return nextP.overlaps(p);
    }

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public Periodo getPeriodoDeAtividade() {
        return horariosAlocados.getPeriodoTotal();
    }

    public LocalDate getDataAlocacao() {
        return horariosAlocados.dataAlocacao();
    }

    public LocalDate getDataExpiracao() {
        return horariosAlocados.dataExpiracao();
    }

    public LocalDate getData() {
        return horariosAlocados.dataAlocacao();
    }

    public Horario getHorario() {
        return new Horario(getHoraInicio(), getHoraFim());
    }

    public DiaDaSemana getDiaSemana() {
        return DiaDaSemana.of(getDataAlocacao().getDayOfWeek());
    }

    public LocalTime getHoraInicio() {
        return horariosAlocados.horario().inicio;
    }

    public LocalTime getHoraFim() {
        return horariosAlocados.horario().fim;
    }

    public String getAssunto() {
        return assunto;
    }

    public Sala getSala() {
        return sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void addEquipamento(Equipamento e) {
        equipamentos.add(e);
    }

    public boolean isAtiva() {
        return ativa && !isGone();
    }

    public void disable() {
        ativa = false;
    }

    public boolean isGone() {
        return LocalDateTime.now().isAfter(getPeriodoDeAtividade().fim);
    }

    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva reserva)) return false;
        return getDataAlocacao().equals(reserva.getDataAlocacao()) && getHoraInicio().equals(reserva.getHoraInicio()) && getHoraFim().equals(reserva.getHoraFim()) && getSala().equals(reserva.getSala()) && getFuncionario().equals(reserva.getFuncionario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDataAlocacao(), getHoraInicio(), getHoraFim(), getSala(), getFuncionario(), getPrioridade());
    }

    @Override
    public String toString() {
        return getTipoReserva() +
                " assunto='" + assunto + '\'' +
                ", prioridade=" + getPrioridade() +
                ", sala=" + sala +
                ", funcionario=" + funcionario +
                ", ativa=" + isAtiva() +
                ", periodo=" + getPeriodoDeAtividade() +
                ", dia-da-semana=" + getDiaSemana() +
                '}';
    }
}