package com.example.bookaroom.views.abstractView.equipamentos;

import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.BookARoomConnect;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.abstractView.inputs.SelectInputComponente;

public abstract class RequisitarEquipamentoAbstract implements FlowComponente {
    SelectInputComponente<Equipamento> equipamentoSelectInput;

    public RequisitarEquipamentoAbstract(Reserva reserva, SelectInputComponente<Equipamento> equipamentoSelectInput) {
        this.equipamentoSelectInput = equipamentoSelectInput;
        equipamentoSelectInput.setOptions(
                bookARoomApi().request().equipamentosDisponiveis(reserva.getDiaSemana(), reserva.getPeriodoDeAtividade(), reserva.getHorario())
        );
    }

    public void requisitar(Reserva reserva) {
        bookARoomApi().request().requisitarEquipamento(reserva, equipamentoSelectInput.get());
    }

    protected BookARoomConnect bookARoomApi() {
        return new BookARoomConnect();
    }
}
