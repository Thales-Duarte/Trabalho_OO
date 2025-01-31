package main.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.services.*;

public class Medico extends Pessoa {
    private String crm;
    private String especialidade;
    private List<Consulta> historicoConsultas = new ArrayList<>();

    public Medico(String nome, String cpf, LocalDate dataNascimento, String crm, String especialidade) {
        super(nome, cpf, dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public List<Consulta> getHistoricoConsultas() {
        return new ArrayList<>(historicoConsultas);
    }

    public void adicionarConsulta(Consulta consulta) {
        historicoConsultas.add(consulta);
    }
}