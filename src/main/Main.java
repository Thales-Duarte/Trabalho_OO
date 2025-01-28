package main;
import main.entities.*;
import main.enume.Status;
import main.excecoes.*;
import main.services.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaGerenciamentoClinica sistema = new SistemaGerenciamentoClinica();
        List<Consulta> consultasExistentes = new ArrayList<>();
        List<Paciente> pacientes = new ArrayList<>();
        List<Medico> medicos = new ArrayList<>();
        

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Clínica ===");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Cadastrar Médico");
            System.out.println("3. Agendar Consulta");
            System.out.println("4. Prescrever Exame");
            System.out.println("5. Visualizar Consultas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do paciente: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF do paciente: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Data de nascimento (YYYY-MM-DD): ");
                    LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
                    pacientes.add(new Paciente(nome, cpf, dataNascimento));
                    System.out.println("Paciente cadastrado com sucesso!");
                }
                case 2 -> {
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
                case 3 -> {
                    if (pacientes.isEmpty() || medicos.isEmpty()) {
                        System.out.println("É necessário cadastrar pacientes e médicos antes de agendar uma consulta.");
                        break;
                    }

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
                case 4 -> {
                    if (consultasExistentes.isEmpty()) {
                        System.out.println("Nenhuma consulta disponível para prescrever exame.");
                        break;
                    }

                    System.out.println("Selecione a consulta para prescrever exame: ");
                    for (int i = 0; i < consultasExistentes.size(); i++) {
                        Consulta consulta = consultasExistentes.get(i);
                        System.out.println((i + 1) + ". Paciente: " + consulta.getPaciente().getNome() + ", Médico: " + consulta.getMedico().getNome() + ", Data: " + consulta.getDataConsulta());
                    }
                    int consultaIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.print("Tipo de exame: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Custo do exame: ");
                    double custo = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Data de validade do exame (YYYY-MM-DD): ");
                    LocalDate dataValidade = LocalDate.parse(scanner.nextLine());

                    Consulta consultaSelecionada = consultasExistentes.get(consultaIndex);
                    Exame exame = new Exame(tipo, LocalDate.now(), custo, consultaSelecionada, dataValidade);
                    consultaSelecionada.adicionarExame(exame); 
                    System.out.println("Exame prescrito com sucesso: " + tipo);
                }
                case 5 -> {
                    if (consultasExistentes.isEmpty()) {
                        System.out.println("Nenhuma consulta cadastrada.");
                    } else {
                        System.out.println("=== Consultas ===");
                        for (Consulta consulta : consultasExistentes) {
                            System.out.println("Paciente: " + consulta.getPaciente().getNome() + ", Médico: " + consulta.getMedico().getNome() + ", Data: " + consulta.getDataConsulta() + ", Status: " + consulta.getStatus());
                        }
                    }
                }
                case 6 -> {
                    System.out.println("Encerrando o sistema. Até logo!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
