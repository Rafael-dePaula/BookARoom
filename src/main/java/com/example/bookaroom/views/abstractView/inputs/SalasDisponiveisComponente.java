package com.example.bookaroom.views.abstractView.inputs;

import com.example.bookaroom.bookaroom.campus.Sala;

import java.util.Map;

public abstract class SalasDisponiveisComponente implements InputComponente<Sala> {
    public abstract void updateOption(Sala sala, Boolean disponivel);

    public abstract void updateOptions(Map<Sala, Boolean> salaBooleanMap);
}
