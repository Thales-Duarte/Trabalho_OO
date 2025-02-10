package main.menu;

import main.controller.SistemaGerenciamentoClinica;
import main.entities.*;
import main.excecoes.ConsultaDataInvalidaException;
import main.excecoes.ConsultaForaDoHorarioException;
import main.excecoes.HorarioIndisponivelException;
import main.excecoes.MedicoJaCadastradoException;
import main.excecoes.PacienteJaCadastradoException;
import main.excecoes.PagamentoPendenteException;
import main.services.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import main.enums.*;;

public class MenuFuncionario implements Menu {
    private final Scanner scanner;
    private final SistemaGerenciamentoClinica sistema;
    

    public MenuFuncionario(Scanner scanner, SistemaGerenciamentoClinica sistema) {
        this.scanner = scanner;
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
            System.out.println("5. Atualizar Banco de dados");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> cadastrarMedico();
                case 3 -> agendarConsulta();
                case 4 -> prescrever();
                case 5 -> AtualizarBanco();
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
        
        Paciente paciente = new Paciente(nome, cpf, dataNascimento); 
        
        try {
        sistema.cadastrarPaciente(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    } catch (PacienteJaCadastradoException e) {
        System.out.println("Erro: " + e.getMessage());
    }}

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
         Medico novoMedico = new Medico(nome, cpf, dataNascimento, crm, especialidade);

    try {
        sistema.cadastrarMedico(novoMedico);
        System.out.println("Médico cadastrado com sucesso!");
    } catch (MedicoJaCadastradoException e) {
        System.out.println("Erro ao cadastrar médico: " + e.getMessage());
    }
}
       
    
private void agendarConsulta() {

    if (sistema.getPacientes().isEmpty() || sistema.getMedicos().isEmpty()) {
        System.out.println("É necessário cadastrar pacientes e médicos antes de agendar uma consulta.");
        exibirMenu();
        return;  // Saímos da função para evitar erros
    }

    try {
        // Seleção do paciente
        System.out.println("Selecione o paciente: ");
        for (int i = 0; i < sistema.getPacientes().size(); i++) {
            System.out.println((i + 1) + ". " + sistema.getPacientes().get(i).getNome());
        }
        int pacienteIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (pacienteIndex < 0 || pacienteIndex >= sistema.getPacientes().size()) {
            System.out.println("Erro: Paciente inválido.");
            return;
        }

        // Seleção do médico
        System.out.println("Selecione o médico: ");
        for (int i = 0; i < sistema.getMedicos().size(); i++) {
            System.out.println((i + 1) + ". " + sistema.getMedicos().get(i).getNome() + " - " + sistema.getMedicos().get(i).getEspecialidade());
        }
        int medicoIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (medicoIndex < 0 || medicoIndex >= sistema.getMedicos().size()) {
            System.out.println("Erro: Médico inválido.");
            return;
        }

        // Entrada da data e hora
        System.out.print("Data da consulta (YYYY-MM-DD): ");
        LocalDate dataConsulta = LocalDate.parse(scanner.nextLine());
        System.out.print("Hora de início (HH:MM): ");
        LocalTime horarioInicio = LocalTime.parse(scanner.nextLine());

        boolean tentativaAgendamento = true;

        while (tentativaAgendamento) {
            try {
                Consulta consulta = new Consulta(dataConsulta, horarioInicio, 30, Status.AGENDADA, 
                sistema.getMedicos().get(medicoIndex), sistema.getPacientes().get(pacienteIndex));
                sistema.agendarConsulta(consulta);
                System.out.println("Consulta agendada com sucesso!");
                tentativaAgendamento = false;  // Sai do loop
            } catch (HorarioIndisponivelException e) {
                System.out.println("Erro: Horário indisponível. " + e.getMessage());
                tentativaAgendamento = false;  // Sai do loop
            } catch (PagamentoPendenteException e) {
                System.out.println("Erro: Pagamento pendente. " + e.getMessage());

                boolean respostaValida = false;
                while (!respostaValida) {
                    System.out.println("O pagamento já foi efetuado (Sim/Nao)? ");
                    String confirma = scanner.nextLine().trim();

                    if (confirma.equalsIgnoreCase("Sim")) {
                        sistema.getPacientes().get(pacienteIndex).setPossuiPagamentoPendente(false);
                        respostaValida = true;
                        System.out.println("Pagamento confirmado! Tentando reagendar a consulta...");
                    } else if (confirma.equalsIgnoreCase("Nao")) {
                        System.out.println("Pagamento ainda pendente. Não é possível agendar.");
                        return;
                    } else {
                        System.out.println("Resposta inválida! Digite apenas 'Sim' ou 'Nao'.");
                    }
                }
            } catch (ConsultaForaDoHorarioException e) {
                System.out.println("Erro: A consulta está fora do horário permitido. " + e.getMessage());
                tentativaAgendamento = false;  // Sai do loop
            } catch (ConsultaDataInvalidaException e) {
                System.out.println("Erro: Data inválida para consulta. " + e.getMessage());
                tentativaAgendamento = false;  // Sai do loop
            }
        }
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
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
                case 1 -> sistema.prescreverExames();
                case 2 -> sistema.prescreverMedicamentos();
                case 3 -> sistema.prescreverTratamentos();
                case 4 -> exibirMenu();
                default -> System.out.println("Opção inválida.");
            }
        }
    }
    
    
    
    
    private void AtualizarBanco(){
        while (true) {
            System.out.println("\n=== Menu Funcionário ===");
            System.out.println("1. Atualizar");
            System.out.println("2. Excluir");
            System.out.println("3. Buscar");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> sistema.atualizarPessoa();
                case 2 -> sistema.excluirPessoa();
                case 3 -> sistema.buscarPessoa();
                case 4 -> exibirMenu();
                default -> System.out.println("Opção inválida.");
            }
        }

    }

}

    