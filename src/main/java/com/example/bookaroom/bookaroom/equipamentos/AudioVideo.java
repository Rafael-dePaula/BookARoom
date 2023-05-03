package com.example.bookaroom.bookaroom.equipamentos;

import java.util.Collection;
import java.util.Set;

public class AudioVideo extends Equipamento{
    final Set<String> conectores;

    public AudioVideo(String nome, Collection<String> conectores) {
        super(nome);
        this.conectores = Set.copyOf(conectores);
    }
}
