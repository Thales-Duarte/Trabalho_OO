package main.services;

import java.time.LocalDate;

public class Medicamento extends Prescricao {
    private String nome;
    private String dosagem;

    public Medicamento(String nome, String dosagem, Consulta consultaAssociada, LocalDate dataValidade) {
        super(consultaAssociada, dataValidade);
        this.nome = nome;
        this.dosagem = dosagem;
    }

    public String getNome() {
        return nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public Consulta getConsulta(){
        return consultaAssociada;
    }
}