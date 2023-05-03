package com.example.bookaroom.bookaroom.equipamentos;

import java.util.Collection;
import java.util.Set;

public class Controles extends Equipamento{
    final Set<String> tipo;
    static final String PREFIX = "CON";

    public Controles(String nome, Collection<String> tipo) {
        super(nome);
        this.tipo = Set.copyOf(tipo);
    }
}