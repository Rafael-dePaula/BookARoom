package com.example.bookaroom.bookaroom;

import com.example.bookaroom.bookaroom.campus.*;
import com.example.bookaroom.bookaroom.campus.Campus;
import com.example.bookaroom.bookaroom.campus.Predio;
import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.DiaDaSemana;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.periodo.Semestre;
import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;
import com.example.bookaroom.bookaroom.reserva.Reuniao;
import com.example.bookaroom.bookaroom.reserva.TipoReserva;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class BookARoom {
    String sessionFuncionario;
    CampusControlador campusControlador;

    public BookARoom(String nomeCampus, String nomeFuncionario) {
        campusControlador = new CampusControlador(nomeCampus);

        if (campusControlador.getCampus() == null) {
            throw new IllegalStateException("Campus " + nomeCampus + " não encontrado");
        }

        Funcionario funcionario = campusControlador.getFuncionario(nomeFuncionario);

        if (funcionario == null) {
            throw new IllegalStateException("Funcionario " + nomeFuncionario +" não encontrado no Campus " + nomeCampus);
        }

        sessionFuncionario = nomeFuncionario;
    }

    private Funcionario getFuncionario() {
        return campusControlador.getFuncionario(sessionFuncionario);
    }

    public List<Reserva> getMinhasReservas() {
        return campusControlador
                .buscarReservas()
                .filtrarPor(getFuncionario());
    }

    public List<Semestre> getSemestres() {
        return campusControlador.getCampus().getSemestres();
    }

    public List<Predio> getPredios() {
        return campusControlador.getCampus().getPredios();
    }

    public HashMap<Sala, Boolean> disponibilidadeDeSalas(Reservavel ...filtros) {
        return campusControlador.salasDisponiveis(0, filtros);
    }

    public HashMap<Sala, Boolean> disponibilidadeDeSalas(TipoReserva tipo, Reservavel ...filtros) {
        return campusControlador.salasDisponiveis(tipo.prioridade, filtros);
    }

    public HashMap<Sala, List<Reserva>> reservasAtivasPorSala(Reservavel ...filtros) {
        Campus campus = campusControlador.getCampus();

        return campusControlador
                .buscarReservas(campus, filtros)
                .ativas()
                .groupBy(campus.getSalas());
    }

    public List<Equipamento> equipamentosDisponiveis(Reservavel ...filtros) {
        return campusControlador.equipamentosDisponiveis(filtros);
    }

    public Reuniao cadastrarReuniao(Periodo periodo, Sala sala, String assunto) {
        return campusControlador.cadastrarReuniao(
                periodo,
                getFuncionario(),
                sala,
                assunto
                );
    }

    public Reuniao cadastrarReuniao(LocalDate data, Horario horario, Sala sala, String assunto) {
        return campusControlador.cadastrarReuniao(
                Periodo.of(data, horario),
                getFuncionario(),
                sala,
                assunto
        );
    }

    public void cadastrarAula(Semestre semestre, DiaDaSemana dia, Horario horario, Sala sala, String assunto) {
        campusControlador.cadastrarAula(
                semestre,
                horario,
                dia,
                getFuncionario(),
                sala,
                assunto
        );
    }

    public void requisitarEquipamento(Reserva reserva, Equipamento equipamento) {
        campusControlador.requisitarEquipamento(reserva, equipamento);
    }

    public <T extends  Equipamento> T cadastrarEquipamento(T equipamento) {
        return campusControlador.cadastrarEquipamento(equipamento);
    }
}
