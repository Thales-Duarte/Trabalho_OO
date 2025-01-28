package main.services;

import java.time.LocalDate;


public abstract class BasePrescricao {
    private Consulta consultaAssociada;
    private LocalDate dataValidade;

    public BasePrescricao(Consulta consultaAssociada, LocalDate dataValidade) {
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
