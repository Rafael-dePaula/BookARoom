package com.example.bookaroom.views.terminal.equipamentos;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.abstractView.equipamentos.RequisitarEquipamentoAbstract;
import com.example.bookaroom.views.terminal.inputs.OptionSelector;

public class RequisitarEquipamento extends RequisitarEquipamentoAbstract {
    Reserva reserva;

    public RequisitarEquipamento(Reserva reserva) {
        super(reserva, new OptionSelector<>("Selecione um equipamento:\nEquipamentos Disponiveis para alocação: "));
        this.reserva = reserva;
    }

    @Override
    public void render() {
        System.out.println("Requisitar Equipamento: ");
    }

    @Override
    public FlowComponente next() {
        return null;
    }

    @Override
    public FlowComponente run() {
        render();
        requisitar(reserva);
        return next();
    }
}
