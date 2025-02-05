package main.controller;

import main.entities.*;
import main.excecoes.*;
import main.services.*;
import main.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SistemaGerenciamentoClinica {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Método para cadastrar paciente
    public void cadastrarPaciente(Paciente novoPaciente) throws PacienteJaCadastradoException {
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(novoPaciente.getCpf())) {
                throw new PacienteJaCadastradoException("Paciente com CPF " + novoPaciente.getCpf() + " já está cadastrado.");
            }
        }
        pacientes.add(novoPaciente);
    }

    // Método para cadastrar médico
    public void cadastrarMedico(Medico novoMedico) throws MedicoJaCadastradoException {
        for (Medico m : medicos) {
            if (m.getCpf().equals(novoMedico.getCpf()) || m.getCrm().equals(novoMedico.getCrm())) {
                throw new MedicoJaCadastradoException("Médico com CPF " + novoMedico.getCpf() + " ou CRM " + novoMedico.getCrm() + " já está cadastrado.");
            }
        }
        medicos.add(novoMedico);
    }

    // Método para agendar consultas
    public void agendarConsulta(Consulta consulta) 
            throws HorarioIndisponivelException, PagamentoPendenteException, ConsultaForaDoHorarioException, ConsultaDataInvalidaException {
        
        if (consulta.getHorarioInicio().isBefore(LocalTime.of(8, 0)) || 
            consulta.getHorarioInicio().isAfter(LocalTime.of(18, 0))) {
            throw new ConsultaForaDoHorarioException("Consulta fora do horário de expediente (8h às 18h).");
        }

        if (consulta.getDataConsulta().isBefore(LocalDate.now())) {
            throw new ConsultaDataInvalidaException("Data da consulta inválida.");
        }

        for (Consulta c : consulta.getMedico().getHistoricoConsultas()) {
            if (c.getDataConsulta().equals(consulta.getDataConsulta()) &&
                c.getHorarioInicio().equals(consulta.getHorarioInicio()) &&
                c.getMedico().equals(consulta.getMedico())) {
                throw new HorarioIndisponivelException("O médico já possui uma consulta neste horário.");
            }
        }

        if (consulta.getPaciente().isPossuiPagamentoPendente()) {
            throw new PagamentoPendenteException("O paciente possui pagamentos pendentes.");
        }

        consulta.getPaciente().adicionarConsulta(consulta);
        consulta.getMedico().adicionarConsulta(consulta);
    }

    // Método para prescrever exames
    public void prescreverExames() {
        System.out.println("Digite o CPF do paciente:");
        String cpf = scanner.nextLine();
       
        Paciente pacienteEncontrado = (Paciente) Pessoa.buscar(cpf, pacientes);
        
        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
            System.out.println("Digite o tipo do exame:");
            String tipo = scanner.nextLine();
            System.out.println("Digite o custo do exame:");
            double custo = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Digite a validade do exame (AAAA-MM-DD):");
            LocalDate dataValidade = LocalDate.parse(scanner.nextLine());

            Exame exame = new Exame(Tipo.valueOf(tipo), LocalDate.now(), custo, pacienteEncontrado, dataValidade);
            pacienteEncontrado.getPrescricoes().add(exame);
            System.out.println("Exame prescrito com sucesso!");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    // Método para prescrever medicamentos
    public void prescreverMedicamentos() {
        System.out.println("Digite o CPF do paciente:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o nome do medicamento:");
        String nome = scanner.nextLine();
        System.out.println("Digite a dosagem:");
        String dosagem = scanner.nextLine();

        Paciente pacienteEncontrado = (Paciente) Pessoa.buscar(cpf, pacientes);

        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
            Medicamento medicamento = new Medicamento(nome, dosagem, pacienteEncontrado);
            pacienteEncontrado.getPrescricoes().add(medicamento);
            System.out.println("Medicamento prescrito com sucesso!");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    // Método para prescrever tratamentos
    public void prescreverTratamentos() {
        System.out.println("Digite o CPF do paciente:");
        String cpf = scanner.nextLine();
        System.out.println("Digite a descrição do tratamento:");
        String descricao = scanner.nextLine();
        System.out.println("Digite a duração em dias:");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Paciente pacienteEncontrado = (Paciente) Pessoa.buscar(cpf, pacientes);

        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
            Tratamento tratamento = new Tratamento(descricao, duracao, pacienteEncontrado);
            pacienteEncontrado.getPrescricoes().add(tratamento);
            System.out.println("Tratamento prescrito com sucesso!");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    // Método para buscar pacientes ou médicos
    public void buscarPessoa() {
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();

        Pessoa pessoaEncontrada = Pessoa.buscar(cpf, pacientes);
        if (pessoaEncontrada == null) {
            pessoaEncontrada = Pessoa.buscar(cpf, medicos);
        }

        if (pessoaEncontrada != null) {
            System.out.println("Pessoa encontrada: " + pessoaEncontrada.getNome());
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    // Método para atualizar pacientes ou médicos
    public void atualizarPessoa() {
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();

        Pessoa pessoaEncontrada = Pessoa.buscar(cpf, pacientes);
        if (pessoaEncontrada == null) {
            pessoaEncontrada = Pessoa.buscar(cpf, medicos);
        }

        if (pessoaEncontrada != null) {
            System.out.println("Pessoa encontrada: " + pessoaEncontrada.getNome());
            System.out.println("Novo nome:");
            String nome = scanner.nextLine();
            System.out.println("Novo CPF:");
            String novoCpf = scanner.nextLine();
            System.out.print("Data de nascimento (YYYY-MM-DD): ");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

            pessoaEncontrada.atualizar(nome, novoCpf, dataNascimento);
            System.out.println("Dados atualizados com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    // Método para excluir pacientes ou médicos
    public void excluirPessoa() {
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();

        if (Pessoa.deletar(cpf, pacientes) || Pessoa.deletar(cpf, medicos)) {
            System.out.println("Pessoa excluída com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    // Getters
    public List<Paciente> getPacientes() { return pacientes; }
    public List<Medico> getMedicos() { return medicos; }
    
}
