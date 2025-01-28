package main.services;

import java.time.LocalDate;


public class Exame extends BasePrescricao {
    private String tipo;
    private LocalDate dataPrescricao;
    private LocalDate dataRealizacao;
    private String resultado;
    private double custo;

    public Exame(String tipo, LocalDate dataPrescricao, double custo, Consulta consultaAssociada, LocalDate dataValidade) {
        super(consultaAssociada, dataValidade);
        this.tipo = tipo;
        this.dataPrescricao = dataPrescricao;
        this.custo = custo;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getDataPrescricao() {
        return dataPrescricao;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public double getCusto() {
        return custo;
    }
}