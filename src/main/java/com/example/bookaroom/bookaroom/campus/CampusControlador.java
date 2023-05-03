package com.example.bookaroom.bookaroom.campus;

import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.DiaDaSemana;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.periodo.Semestre;
import com.example.bookaroom.bookaroom.reserva.*;
import com.example.bookaroom.dados.DataSource;

import java.util.*;
import java.util.function.Consumer;

public class CampusControlador {
    String campusNome;

    public CampusControlador(String campusNome) {
        this.campusNome = campusNome;
    }

    public ReservaList buscarReservas(Reservavel ...filtros) {
        return getCampus().getReservas(filtros);
    }

    public ReservaList buscarReservas(Campus campus, Reservavel ...filtros) {
        return campus.getReservas(filtros);
    }

    public Campus getCampus() {
        return DataSource.fetch(this::getCampus);
    }

    private Campus getCampus(List<Campus> campi) {
        return campi.stream()
                .filter(campus -> campus.getNome().equals(campusNome))
                .findFirst()
                .orElse(null);
    }

    private synchronized void updateCampus(Consumer<Campus> updateFunc) {
        DataSource.transaction((campi) -> {
            Campus campus = getCampus(campi);
            updateFunc.accept(campus);
        });
    }

    public HashMap<Sala, Boolean> salasDisponiveis(int prioridade, Reservavel ...filtros) {
        ReservaList buscarReserva = buscarReservas().filtrarPor(filtros);

        return new HashMap<>() {{
            getCampus().getSalas().forEach(
                    sala -> put(sala, buscarReserva.filtrarPor(sala).podemSerSobrescritas(prioridade)));
        }};
    }

    public List<Equipamento> equipamentosDisponiveis(Reservavel ...filtros) {
        ReservaList buscarReserva = buscarReservas(filtros).ativas();

        return new ArrayList<>() {{
            getCampus().getEquipamentos().forEach(equipamento -> {
                if(buscarReserva.filtrarPor(equipamento).isEmpty())
                    add(equipamento);
            });
        }};
    }

    public Funcionario getFuncionario(String nome) {
        for(Funcionario funcionario : getCampus().getFuncionarios()) {
            if(funcionario.nome().equals(nome)){
                return funcionario;
            }
        }
        return null;
    }

    public Reuniao cadastrarReuniao(Periodo periodo, Funcionario funcionario, Sala sala, String assunto) {
        Reuniao novaReserva = new Reuniao(periodo, funcionario, sala, assunto);

        updateCampus(campus -> {
            campus.addReserva(novaReserva);
        });

        return novaReserva;
    }

    public Aula cadastrarAula(Semestre semestre, Horario horario, DiaDaSemana diaSemana, Funcionario funcionario, Sala sala, String assunto) {
        Aula novaReserva = new Aula(semestre, diaSemana, horario, funcionario, sala, assunto);

        updateCampus(campus -> {
            campus.addReserva(novaReserva);
        });

        return novaReserva;
    }

    public <E extends Equipamento> E cadastrarEquipamento(E equipamento) {
        updateCampus(campus -> campus.cadastrarEquipamento(equipamento));
        return equipamento;
    }

    public void requisitarEquipamento(Reserva reserva, Equipamento equipamento) {
        updateCampus(campus -> campus.alocarEquipamento(reserva, equipamento));
    }
}
