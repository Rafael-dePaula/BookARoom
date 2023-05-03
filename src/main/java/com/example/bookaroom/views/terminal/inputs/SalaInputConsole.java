package com.example.bookaroom.views.terminal.inputs;

import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.views.abstractView.inputs.SalasDisponiveisComponente;

import java.util.Map;


public class SalaInputConsole extends SalasDisponiveisComponente {
    OptionSelector<Sala> salaInput;

    public SalaInputConsole() {
        salaInput = new OptionSelector<>("Selecione uma sala:");
    }

    @Override
    public Sala get() {
        return salaInput.get();
    }

    @Override
    public void render() {
        salaInput.render();
    }

    @Override
    public void updateOption(Sala sala, Boolean disponivel) {
        salaInput.addOption(sala.toString() + "(" + (disponivel ? "disponivel" : "ocupada") + ")", sala);
    }

    @Override
    public void updateOptions(Map<Sala, Boolean> values) {
        values.keySet().stream().sorted().forEach(sala -> updateOption(sala, values.get(sala)));
    }
}
