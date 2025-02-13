package main.services;

import java.time.LocalDate;
import main.entities.*;

import main.enums.Tipo;


public class Exame extends Prescricao {
    private Tipo tipo;
    private LocalDate dataRealizacao;
    private double custo;

    public Exame(Tipo tipo, LocalDate dataRealizacao, double custo, Paciente paciente, LocalDate dataValidade) {
        super(paciente, dataValidade);
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


}