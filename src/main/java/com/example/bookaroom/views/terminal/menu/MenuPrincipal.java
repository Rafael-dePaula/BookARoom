package com.example.bookaroom.views.terminal.menu;

import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.terminal.equipamentos.CadastrarEquipamento;
import com.example.bookaroom.views.terminal.inputs.OptionSelector;
import com.example.bookaroom.views.terminal.reservas.CadastrarAula;
import com.example.bookaroom.views.terminal.reservas.CadastrarReuniao;
import com.example.bookaroom.views.terminal.reservas.MinhasReservas;


public class MenuPrincipal implements FlowComponente {
    OptionSelector<FlowComponente> options;

    {
        options = new OptionSelector<>("Escolha uma opção: ");
        options.addOption("Minhas Reservas", new MinhasReservas());
        options.addOption("Cadastrar Equipamento", new CadastrarEquipamento());
        options.addOption("Cadastrar Reunião", new CadastrarReuniao());
        options.addOption("Cadastrar Aula", new CadastrarAula());
        options.addOption("Sair", null);
    }

    @Override
    public void render() {
        System.out.println("BookARoom");
    }

    @Override
    public FlowComponente next() {
        return options.get();
    }
}
