package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.abstractView.reservas.CadastrarReuniaoAbstract;

import com.example.bookaroom.views.terminal.inputs.DataInputConsole;
import com.example.bookaroom.views.terminal.inputs.HorarioInputConsole;
import com.example.bookaroom.views.terminal.inputs.SalaInputConsole;
import com.example.bookaroom.views.terminal.inputs.TextInputConsole;

public class CadastrarReuniao extends CadastrarReuniaoAbstract {

    static private SalaInputConsole createSalaInput() {
        return new SalaInputConsole();
    }

    static private TextInputConsole createAssuntoInput() {
        return new TextInputConsole("\tDigite o Assunto: ", (str) -> !str.isEmpty());
    }

    public CadastrarReuniao() {
        super(createSalaInput(), createAssuntoInput(), new HorarioInputConsole(), new DataInputConsole());
    }

    @Override
    public void render() {
        System.out.println("Cadastrar Reunião");
    }

    @Override
    public FlowComponente next() {
        return null;
    }

    @Override
    public FlowComponente run() {
        render();
        cadastrar();
        return next();
    }
}
