package com.example.bookaroom.bookaroom.reserva;

public enum TipoReserva {
    AULA(1, 7), REUNIAO(0, 0);
    public final int prioridade;
    public final int frequencia;

    TipoReserva(int prioridade, int intervaloDeOcorrencia) {
        this.prioridade = prioridade;
        this.frequencia = intervaloDeOcorrencia;
    }
}
