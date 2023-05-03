package com.example.bookaroom.views.terminal.inputs;

import com.example.bookaroom.bookaroom.equipamentos.Equipamento;

public class EquipamentoSelectConsole extends OptionSelector<Equipamento> {
    public EquipamentoSelectConsole(String title, Iterable<Equipamento> values) {
        super(title, values);
    }
}
