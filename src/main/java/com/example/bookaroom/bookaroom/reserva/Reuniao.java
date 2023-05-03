package com.example.bookaroom.bookaroom.reserva;

import com.example.bookaroom.bookaroom.campus.Funcionario;
import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.periodo.AlocacoesHorario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Reuniao extends Reserva {

    public Reuniao(Periodo periodo, Funcionario funcionario, Sala sala, String assunto) {
        super(AlocacoesHorario.unico(periodo.inicio.toLocalDate(), new Horario(periodo.inicio.toLocalTime(), periodo.fim.toLocalTime())), funcionario, sala, assunto, new ArrayList<>());
    }


    @Override
    public TipoReserva getTipoReserva() {
        return TipoReserva.REUNIAO;
    }

}
