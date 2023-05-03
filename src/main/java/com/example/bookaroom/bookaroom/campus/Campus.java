package com.example.bookaroom.bookaroom.campus;


import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.Semestre;
import com.example.bookaroom.bookaroom.reserva.*;

import java.io.*;
import java.util.*;

public class Campus implements Serializable {
    private final String nome;
    private final String endereco;
    private List<Predio> predios;
    private final List<Funcionario> funcionarios;
    private final List<Equipamento> equipamentos;
    private final ReservaList reservas;
    private final List<Semestre> semestres;

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Campus(
            String nome,
            String endereco,
            List<Predio> predios,
            List<Funcionario> funcionarios,
            List<Equipamento> equipamentos,
            List<Reserva> reservas,
            List<Semestre> semestres) {
        this.nome = nome;
        this.endereco = endereco;
        setPredios(predios);
        this.funcionarios = funcionarios;
        this.equipamentos = equipamentos;
        this.reservas = new ReservaList(reservas);
        this.semestres = semestres;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Predio> getPredios() { return predios; }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public List<Semestre> getSemestres() {
        return semestres;
    }

    public List<Sala> getSalas() {
        return new ArrayList<>() {{
            predios.forEach(predio -> addAll(predio.getSalas()));
        }};
    }

    protected void setPredios(List<Predio> predios) {
        this.predios = new ArrayList<>();
        predios.forEach(this::addPredio);
    }

    public ReservaList getReservas() {
        return reservas;
    }

    public ReservaList getReservas(Reservavel ...filtros) {
        return reservas.filtrarPor(filtros);
    }

    public void addPredio(Predio predio) {
        predio.setCampus(this);
        predios.add(predio);
    }

    public void addReserva(Reserva reserva) {
        validarCadastroReserva(reserva);
        reservas.add(reserva);
    }

    public void validarCadastroReserva(Reserva reserva) {
        ReservaList conflitos = reservas.getConflitos(reserva);

        conflitos.forEach((reservaConflito) -> {
            if(reservaConflito.getPrioridade() >= reserva.getPrioridade()) {
                throw new IllegalStateException("Reserva tem conflitos com reservas de maior ou igual prioridade");
            }
        });

        conflitos.forEach(Reserva::disable);
    }

    // </editor-fold>

    public void alocarEquipamento(Reserva reserva, Equipamento equipamento) {
        int index = getReservas().indexOf(reserva);
        if(index != -1) {
            getReservas().get(index).addEquipamento(equipamento);
        }
    }

    public void cadastrarEquipamento(Equipamento equipamento) {
        int number = getEquipamentos().size();
        equipamento.setPatrimonio(nome + "-equip-" + number);
        equipamentos.add(equipamento);
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campus campus)) return false;
        return getNome().equals(campus.getNome()) && getEndereco().equals(campus.getEndereco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getEndereco());
    }


}
