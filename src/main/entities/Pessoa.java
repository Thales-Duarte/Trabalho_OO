package main.entities;

import java.time.LocalDate;
import java.util.List;



public abstract class Pessoa {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    
    public static Pessoa buscar(String cpf, List<? extends Pessoa> pessoas) {
        for (Pessoa p : pessoas) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

   
    public void atualizar(String novoNome, String cpf, LocalDate dataNascimento) {
        this.nome = novoNome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    
    public static boolean deletar(String cpf, List<? extends Pessoa> pessoas) {
        
        if (cpf == null || cpf.isBlank()) {
            return false;
        }
        return pessoas.removeIf(p -> cpf.equals(p.getCpf()));
    }
}
    
