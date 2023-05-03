package com.example.bookaroom.views.abstractView.reservas;

import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.reserva.TipoReserva;
import com.example.bookaroom.views.abstractView.inputs.InputComponente;
import com.example.bookaroom.views.abstractView.inputs.SalasDisponiveisComponente;

import java.time.LocalDate;

public abstract class CadastrarReuniaoAbstract extends CadastrarReservaAbstract {
    InputComponente<LocalDate> dataInput;

    public CadastrarReuniaoAbstract(SalasDisponiveisComponente salaInput, InputComponente<String> assuntoInput, InputComponente<Horario> horarioInput, InputComponente<LocalDate> dataInput) {
        super(salaInput, assuntoInput, horarioInput);
        this.dataInput = dataInput;
    }

    protected void cadastrar() {
        Periodo periodo = Periodo.of(dataInput.get(), horarioInput.get());
        salaInput.updateOptions(bookARoomApi().request().disponibilidadeDeSalas(TipoReserva.REUNIAO, periodo));

        bookARoomApi().request()
                .cadastrarReuniao(
                        periodo,
                        salaInput.get(),
                        assuntoInput.get()
                );
    }
}
