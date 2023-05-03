package com.example.bookaroom.views.abstractView.reservas;

import com.example.bookaroom.bookaroom.periodo.DiaDaSemana;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Semestre;
import com.example.bookaroom.bookaroom.reserva.TipoReserva;
import com.example.bookaroom.views.abstractView.inputs.InputComponente;
import com.example.bookaroom.views.abstractView.inputs.SalasDisponiveisComponente;

public abstract class CadastrarAulaAbstract extends CadastrarReservaAbstract {
    InputComponente<DiaDaSemana> diaInput;
    InputComponente<Semestre> semestreInput;

    public CadastrarAulaAbstract(SalasDisponiveisComponente salaInput, InputComponente<String> assuntoInput, InputComponente<Horario> horarioInput, InputComponente<DiaDaSemana> diaInput, InputComponente<Semestre> semestreInput) {
        super(salaInput, assuntoInput, horarioInput);
        this.diaInput = diaInput;
        this.semestreInput = semestreInput;
    }

    @Override
    protected void cadastrar() {
        Horario horario = horarioInput.get();
        Semestre semestre = semestreInput.get();
        DiaDaSemana dia = diaInput.get();
        salaInput.updateOptions(bookARoomApi().request().disponibilidadeDeSalas(TipoReserva.AULA, horario, semestre, dia));
        bookARoomApi().request().cadastrarAula(
                semestre,
                dia,
                horario,
                salaInput.get(),
                assuntoInput.get());
    }
}
