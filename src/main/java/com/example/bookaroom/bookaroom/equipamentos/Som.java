package com.example.bookaroom.bookaroom.equipamentos;

import java.util.Collection;
import java.util.Set;

public class Som extends Equipamento{
    	final Set<boolean> bluetooth;
	final Set<String> tipo;
	final Set<Integer> volts;
   	static final String PREFIX = "CON";

    public Controles(String nome, Collection<String> tipo, boolean bluetooth, Integer volts) {
        super(nome);
        this.tipo = Set.copyOf(tipo);
	  this.bluetooth= bluetooth;
	  this.volts = volts;
    }
}