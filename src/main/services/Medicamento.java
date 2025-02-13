package main.services;

import main.entities.Paciente;

public class Medicamento extends Prescricao {
    private String nome;
    private String dosagem;

    public Medicamento(String nome, String dosagem, Paciente paciente) {
        super(paciente);
        this.nome = nome;
        this.dosagem = dosagem;
    }

    public String getNome() {
        return nome;
    }

    public String getDosagem() {
        return dosagem;
    }

}