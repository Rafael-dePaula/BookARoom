package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.bookaroom.periodo.Semestre;
import com.example.bookaroom.bookaroom.periodo.DiaDaSemana;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.abstractView.reservas.CadastrarAulaAbstract;
import com.example.bookaroom.views.terminal.Terminal;
import com.example.bookaroom.views.terminal.inputs.HorarioInputConsole;
import com.example.bookaroom.views.terminal.inputs.SalaInputConsole;
import com.example.bookaroom.views.terminal.inputs.TextInputConsole;
import com.example.bookaroom.views.terminal.inputs.OptionSelector;

import java.util.List;

public class CadastrarAula extends CadastrarAulaAbstract {

    public static OptionSelector<Semestre> createSemestreInput() {
        List<Semestre> semestres = Terminal.bookARoomApi().request().getSemestres();
        return new OptionSelector<>("Selecione o semestre: ", semestres);
    }

    public static OptionSelector<DiaDaSemana> createDiaSemanaInput() {
        return new OptionSelector<>("Selecione o dia: ",  DiaDaSemana.toList());
    }

    public CadastrarAula() {
        super(new SalaInputConsole(), new TextInputConsole("Especifique o assunto: "), new HorarioInputConsole(), createDiaSemanaInput(), createSemestreInput());
    }

    public FlowComponente next() {
        return null;
    }

    public FlowComponente run() {
        render();
        cadastrar();
        return next();
    }

    public void render() {
        System.out.println("Cadastrar Aula");
    }

}
