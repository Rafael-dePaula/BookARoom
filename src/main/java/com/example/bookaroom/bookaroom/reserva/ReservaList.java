package com.example.bookaroom.bookaroom.reserva;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class ReservaList extends ArrayList<Reserva> implements Serializable {
    public ReservaList(Collection<Reserva> reservas) {
        super(reservas);
    }

    public ReservaList() {}

    public ReservaList filtrarPor(Reservavel ...reservaveis) {

        Stream<Reserva> reservasStream = this.stream();
        for(Reservavel reservavel : reservaveis) {
            reservasStream = reservasStream.filter(reservavel::contidoEm);
        }

        return new ReservaList(reservasStream.toList());
    }

    public Boolean podemSerSobrescritas(int prioridade) {
        Stream<Reserva> reservasStream = this.stream();
        return reservasStream.noneMatch((reserva -> reserva.getPrioridade() >= prioridade));
    }

    public ReservaList ativas() {
        return new ReservaList(
                stream().filter(Reserva::isAtiva).toList()
        );
    }

    public <T extends Reservavel> HashMap<T, List<Reserva>> groupBy(List<T> reservaveis) {
        return new HashMap<>(){{
            reservaveis.forEach(reservavel -> put(reservavel, stream().filter(reservavel::contidoEm).toList()));
        }};
    }

    public ReservaList getConflitos(Reserva reserva) {
        final Reservavel[] filtrosPeriodo = { reserva.getDiaSemana(), reserva.getHorario(), reserva.getPeriodoDeAtividade()};
        ReservaList reservasNoPeriodo = filtrarPor(filtrosPeriodo).ativas();

        final List<Reservavel> reservaveis = new ArrayList<>() {{
            addAll(reserva.getEquipamentos());
            add(reserva.getFuncionario());
            add(reserva.getSala());
        }};

        return new ReservaList(){{
            reservaveis.forEach(reservavel -> addAll(reservasNoPeriodo.filtrarPor(reservavel)));
        }};
    }
}
