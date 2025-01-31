package main.services;

import java.time.LocalDate;
import main.entities.*;


public abstract class Prescricao {
    protected Paciente paciente;
    private LocalDate dataValidade;

    public Prescricao(Paciente paciente, LocalDate dataValidade) {
        this.paciente = paciente;
        this.dataValidade = dataValidade;
    }

    public Prescricao(Paciente paciente) {
        this.paciente = paciente;
        
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }
}

