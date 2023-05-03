package com.example.bookaroom.views.terminal.inputs;

import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.views.abstractView.inputs.InputComponente;

import java.time.format.DateTimeParseException;

public class HorarioInputConsole implements InputComponente<Horario> {
    static final String PATTERN_HORA = "^([0-1][0-9]|2[0-3]):[0-9][0-9]$";
    TextInputConsole inicioInput = new TextInputConsole("\tInicia às <HH:mm>: ", PATTERN_HORA);
    TextInputConsole fimInput = new TextInputConsole("\tTermina ás <HH:mm>: ", PATTERN_HORA);



    @Override
    public void render() {
        System.out.println("Defina o horario: ");
    }

    public Horario tryGetHorario() {
        String inicio = inicioInput.get();
        String fim = fimInput.get();
        try {
            return new Horario(inicio, fim);
        } catch (DateTimeParseException | IllegalStateException e) {
            return null;
        }
    }

    @Override
    public Horario get() {
        render();
        Horario horario = tryGetHorario();
        while(horario == null) {
            System.out.println("Horario invalido.");
            horario = tryGetHorario();
        }
        return horario;
    }
}
