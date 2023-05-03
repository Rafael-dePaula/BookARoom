package com.example.bookaroom.views.abstractView.inputs;

import com.example.bookaroom.views.abstractView.Componente;

public interface InputComponente<T> extends Componente {
    T get();
}
