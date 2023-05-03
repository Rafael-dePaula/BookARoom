package com.example.bookaroom.bookaroom.reserva;

import com.example.bookaroom.bookaroom.campus.Funcionario;
import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.periodo.Semestre;
import com.example.bookaroom.bookaroom.periodo.DiaDaSemana;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.AlocacoesHorario;

import java.util.ArrayList;

public class Aula extends Reserva {
    Semestre semestre;

    public Aula(Semestre semestre, DiaDaSemana dia, Horario horario, Funcionario funcionario, Sala sala, String assunto) {
        super(AlocacoesHorario.semanal(semestre, dia, horario), funcionario, sala, assunto, new ArrayList<>());
        this.semestre = semestre;
    }

//    public Aula(Semestre semestre, List<HorarioSemanal> horarios, Funcionario funcionario, Sala sala, String assunto) {
//        super(AlocacoesHorario.semanal(semestre, dia, horario), funcionario, sala, assunto, new ArrayList<>());
//        this.semestre = semestre;
//    }

//    private static List<Alocacao> criarAlocacoes(List<HorarioSemanal> horarios) {
        // for horario in horarios
        // data = getNextDataWithDiaSemana (Periodo)
        // while data isBefore Periodo.fim
        //    addAlocacao(data)
          //
//    }

    @Override
    public TipoReserva getTipoReserva() {
        return TipoReserva.AULA;
    }
}
