package com.example.bookaroom.views.abstractView.reservas;

import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.views.BookARoomConnect;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.abstractView.inputs.InputComponente;
import com.example.bookaroom.views.abstractView.inputs.SalasDisponiveisComponente;

public abstract class CadastrarReservaAbstract implements FlowComponente {
    protected SalasDisponiveisComponente salaInput;
    protected InputComponente<String> assuntoInput;
    protected InputComponente<Horario> horarioInput;

    public CadastrarReservaAbstract(SalasDisponiveisComponente salaInput, InputComponente<String> assuntoInput, InputComponente<Horario> horarioInput) {
        this.salaInput = salaInput;
        this.assuntoInput = assuntoInput;
        this.horarioInput = horarioInput;
    }

    abstract protected void cadastrar();

    protected BookARoomConnect bookARoomApi() {
        return new BookARoomConnect();
    }
}
