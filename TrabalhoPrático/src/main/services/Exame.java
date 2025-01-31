package main.services;

import java.time.LocalDate;

import main.enums.Tipo;


public class Exame extends Prescricao {
    private Tipo tipo;
    private LocalDate dataRealizacao;
    private double custo;

    public Exame(Tipo tipo, LocalDate dataRealizacao, double custo, Consulta consultaAssociada, LocalDate dataValidade) {
        super(consultaAssociada, dataValidade);
        this.tipo = tipo;
        this.dataRealizacao = dataRealizacao;
        this.custo = custo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public double getCusto() {
        return custo;
    }

    public Consulta getConsulta(){
        return consultaAssociada;
    }
}