package main.services;


import main.entities.*;

public class Tratamento extends Prescricao {
    private String descricao;
    private int duracaoDias;

    public Tratamento(String descricao, int duracaoDias, Paciente paciente) {
        super(paciente);
        this.descricao = descricao;
        this.duracaoDias = duracaoDias;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracaoDias() {
        return duracaoDias;
    }

    public Paciente getPaciente(){
        return paciente;
    }
}
