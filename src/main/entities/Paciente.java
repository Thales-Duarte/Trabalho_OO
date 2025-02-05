package main.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.services.*;


public class Paciente extends Pessoa {
    private List<Consulta> historicoConsultas = new ArrayList<>();
    private boolean possuiPagamentoPendente;
    private List<Prescricao> prescricoes = new ArrayList<>();

    public Paciente(String nome, String cpf, LocalDate dataNascimento) {
        super(nome, cpf, dataNascimento);
    }

    public List<Consulta> getHistoricoConsultas() {
        return new ArrayList<>(historicoConsultas);
    }

    public boolean isPossuiPagamentoPendente() {
        return possuiPagamentoPendente;
    }

    public void adicionarConsulta(Consulta consulta) {
        historicoConsultas.add(consulta);
    }

    public void setPossuiPagamentoPendente(boolean possuiPagamentoPendente) {
        this.possuiPagamentoPendente = possuiPagamentoPendente;
    }
    
    public List<Prescricao> getPrescricoes(){
        return prescricoes;
    }

}