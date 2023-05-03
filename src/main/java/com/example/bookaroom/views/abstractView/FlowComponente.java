package com.example.bookaroom.views.abstractView;


public interface FlowComponente extends Componente{
    FlowComponente next();

    default FlowComponente run() {
        render();
        return next();
    }
}
