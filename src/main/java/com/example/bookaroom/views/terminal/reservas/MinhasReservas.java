package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.abstractView.FlowComponente;
import com.example.bookaroom.views.terminal.Terminal;
import com.example.bookaroom.views.terminal.inputs.OptionSelector;
import com.example.bookaroom.views.terminal.menu.MenuPrincipal;

import java.util.List;

public class MinhasReservas implements FlowComponente {
    OptionSelector<Reserva> optionSelector;

    @Override
    public void render() {
        List<Reserva> reservas = Terminal.bookARoomApi().request().getMinhasReservas();

        optionSelector = new OptionSelector<>("Minhas Reservas");
        reservas.forEach(reserva -> optionSelector.addOption(reserva.toString(), reserva));
        optionSelector.addOption("Voltar", null);
    }

    @Override
    public FlowComponente next() {
        Reserva reserva = optionSelector.get();
        if(reserva == null) {
            return new MenuPrincipal();
        }

        return new DetalhesReserva(reserva);
    }
}
