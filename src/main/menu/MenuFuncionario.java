package main.menu;

import main.entities.*;
import main.excecoes.ConsultaDataInvalidaException;
import main.excecoes.ConsultaForaDoHorarioException;
import main.excecoes.HorarioIndisponivelException;
import main.excecoes.PagamentoPendenteException;
import main.gerencia.SistemaGerenciamentoClinica;
import main.services.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import main.enums.*;;

public class MenuFuncionario implements Menu {
    private final Scanner scanner;
    private final List<Paciente> pacientes;
    private final List<Medico> medicos;
    private final List<Consulta> consultasExistentes;
    private final List<Prescricao> prescricao;
    
    private final SistemaGerenciamentoClinica sistema;

    public MenuFuncionario(Scanner scanner, List<Paciente> pacientes, List<Medico> medicos, 
                           List<Consulta> consultas, List<Prescricao> prescricao, SistemaGerenciamentoClinica sistema) {
        this.scanner = scanner;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.consultasExistentes = consultas;
        this.prescricao= prescricao;
        this.sistema = sistema;
    }

    @Override
    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Área do Funcionário ===");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Cadastrar Médico");
            System.out.println("3. Agendar Consulta");
            System.out.println("4. Prescrever");
            System.out.println("5. Visualizar Consultas");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> cadastrarMedico();
                case 3 -> agendarConsulta();
                case 4 -> prescrever();
                //case 5 -> visualizarConsultas();
                case 6 -> { return; }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void cadastrarPaciente() {
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do paciente: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de nascimento (YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        pacientes.add(new Paciente(nome, cpf, dataNascimento));
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private void cadastrarMedico() {
        System.out.print("Nome do médico: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do médico: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de nascimento (YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        System.out.print("CRM do médico: ");
        String crm = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        medicos.add(new Medico(nome, cpf, dataNascimento, crm, especialidade));
        System.out.println("Médico cadastrado com sucesso!");
    }

    private void agendarConsulta() {
        
        if (pacientes.isEmpty() || medicos.isEmpty()) {
                System.out.println("É necessário cadastrar pacientes e médicos antes de agendar uma consulta.");
                    exibirMenu();
                    }
        else{
                    System.out.println("Selecione o paciente: ");
                    for (int i = 0; i < pacientes.size(); i++) {
                        System.out.println((i + 1) + ". " + pacientes.get(i).getNome());
                    }
                    int pacienteIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.println("Selecione o médico: ");
                    for (int i = 0; i < medicos.size(); i++) {
                        System.out.println((i + 1) + ". " + medicos.get(i).getNome() + " - " + medicos.get(i).getEspecialidade());
                    }
                    int medicoIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.print("Data da consulta (YYYY-MM-DD): ");
                    LocalDate dataConsulta = LocalDate.parse(scanner.nextLine());
                    System.out.print("Hora de início (HH:MM): ");
                    LocalTime horarioInicio = LocalTime.parse(scanner.nextLine());

                    try {
                        Consulta consulta = new Consulta(dataConsulta, horarioInicio,30, Status.AGENDADA, medicos.get(medicoIndex), pacientes.get(pacienteIndex));
                        sistema.agendarConsulta(consulta, consultasExistentes);
                        System.out.println("Consulta agendada com sucesso!");
                    } catch (HorarioIndisponivelException e) {
                        System.out.println("Erro: Horário indisponível. " + e.getMessage());
                    } catch (PagamentoPendenteException e) {
                        System.out.println("Erro: Pagamento pendente. " + e.getMessage());
                    } catch (ConsultaForaDoHorarioException e) {
                        System.out.println("Erro: A consulta está fora do horário permitido. " + e.getMessage());
                    } catch (ConsultaDataInvalidaException e) {
                        System.out.println("Erro: Data inválida para consulta" + e.getMessage());
                    }
                }

    }

    private void prescrever() {
        while (true) {
            System.out.println("\n=== Menu Funcionário ===");
            System.out.println("1. Prescrever Exames");
            System.out.println("2. Prescrever Medicamentos");
            System.out.println("3. Prescrever Tratamentos");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> prescreverExames();
                case 2 -> prescreverMedicamentos();
                case 3 -> prescreverTratamentos();
                case 4 -> exibirMenu();
                default -> System.out.println("Opção inválida.");
            }
        }
    }
    
    
    private void prescreverExames() {

        System.out.println("Digite o CPF do paciente");
        String cpf = scanner.nextLine();
       
        Paciente pacienteEncontrado = buscarPacientePorCPF(cpf);
        
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
        prescricao.add(exame);
        System.out.println("Exame prescrito com sucesso!");
        } 
        else {
        System.out.println("Paciente não encontrado.");
        exibirMenu();
        }
    
        

        
        
    }

    private void prescreverMedicamentos() {
        System.out.println("Digite o CPF do paciente");
        String cpf = scanner.nextLine();
        System.out.println("Digite o nome do medicamento:");
        String nome = scanner.nextLine();
        System.out.println("Digite a dosagem:");
        String dosagem = scanner.nextLine();

        Paciente pacienteEncontrado = buscarPacientePorCPF(cpf);

        if (pacienteEncontrado != null) {
        System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
        } else {
        System.out.println("Paciente não encontrado.");
        }

        Medicamento medicamento = new Medicamento(nome, dosagem, pacienteEncontrado);
        prescricao.add(medicamento);
        System.out.println("Medicamento prescrito com sucesso!");
    }

    private void prescreverTratamentos() {
        System.out.println("Digite o CPF do paciente");
        String cpf = scanner.nextLine();
        System.out.println("Digite a descrição do tratamento:");
        String descricao = scanner.nextLine();
        System.out.println("Digite a duração em dias:");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Paciente pacienteEncontrado = buscarPacientePorCPF(cpf);

        if (pacienteEncontrado != null) {
        System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
        } else {
        System.out.println("Paciente não encontrado.");
        }

        Tratamento tratamento = new Tratamento(descricao, duracao, pacienteEncontrado);
        prescricao.add(tratamento);
        System.out.println("Tratamento prescrito com sucesso!");
    }

   




    private Paciente buscarPacientePorCPF(String cpf) {
    for (Paciente paciente : pacientes) {
        if (paciente.getCpf().equals(cpf)) {
            return paciente;
        }
    }
    return null; // Retorna null se o paciente não for encontrado
    }
}

        