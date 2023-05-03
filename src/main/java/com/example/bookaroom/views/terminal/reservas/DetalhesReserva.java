package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.terminal.equipamentos.RequisitarEquipamento;
import com.example.bookaroom.views.terminal.inputs.OptionSelector;

public class DetalhesReserva implements FlowComponente {
    OptionSelector<FlowComponente> options;

    public DetalhesReserva(Reserva reserva) {
        options = new OptionSelector<>("Escolha uma opção: ");
        options.addOption("Requisitar Equipamento", new RequisitarEquipamento(reserva));
        options.addOption("Voltar", new MinhasReservas());
    }

    @Override
    public void render() {
        System.out.println("Detalhes de Reserva");
    }

    @Override
    public FlowComponente next() {
        return options.get();
    }
}
