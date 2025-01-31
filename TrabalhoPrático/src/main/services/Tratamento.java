package main.services;

import java.time.LocalDate;

public class Tratamento extends Prescricao {
    private String descricao;
    private int duracaoDias;

    public Tratamento(String descricao, int duracaoDias, Consulta consultaAssociada, LocalDate dataValidade) {
        super(consultaAssociada, dataValidade);
        this.descricao = descricao;
        this.duracaoDias = duracaoDias;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracaoDias() {
        return duracaoDias;
    }

    public Consulta getConsulta(){
        return consultaAssociada;
    }
}
