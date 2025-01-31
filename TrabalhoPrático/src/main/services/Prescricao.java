package main.services;

import java.time.LocalDate;


public abstract class Prescricao {
    protected Consulta consultaAssociada;
    private LocalDate dataValidade;

    public Prescricao(Consulta consultaAssociada, LocalDate dataValidade) {
        this.consultaAssociada = consultaAssociada;
        this.dataValidade = dataValidade;
    }

    public Consulta getConsultaAssociada() {
        return consultaAssociada;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }
}

