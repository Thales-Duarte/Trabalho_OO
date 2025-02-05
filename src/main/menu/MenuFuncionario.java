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
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import main.enums.*;;

public class MenuFuncionario implements Menu {
    private final Scanner scanner;
    private final List<Consulta> consultasExistentes;
    private final List<Prescricao> prescricao;
    private final SistemaGerenciamentoClinica sistema;
    

    public MenuFuncionario(Scanner scanner, 
                           List<Consulta> consultas, List<Prescricao> prescricao, SistemaGerenciamentoClinica sistema) {
        this.scanner = scanner;
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
    } catch (ConsultaDataInvalidaException e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
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
         Medico novoMedico = new Medico(nome, cpf, dataNascimento, crm, especialidade);

    try {
        sistema.cadastrarMedico(novoMedico);
        System.out.println("Médico cadastrado com sucesso!");
    } catch (MedicoJaCadastradoException e) {
        System.out.println("Erro ao cadastrar médico: " + e.getMessage());
    }
}
       
    
private void agendarConsulta() {
    List<Paciente> pacientes = sistema.getPacientes();
    List<Medico> medicos = sistema.getMedicos();    
    
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

        List<Paciente> pacientes = sistema.getPacientes();
    

        System.out.println("Digite o CPF do paciente");
        String cpf = scanner.nextLine();
       
        Paciente pacienteEncontrado =  (Paciente) Pessoa.buscar(cpf, pacientes);
        
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
        
        List<Paciente> pacientes = sistema.getPacientes();
    
        System.out.println("Digite o CPF do paciente");
        String cpf = scanner.nextLine();
        System.out.println("Digite o nome do medicamento:");
        String nome = scanner.nextLine();
        System.out.println("Digite a dosagem:");
        String dosagem = scanner.nextLine();

        Paciente pacienteEncontrado =  (Paciente) Pessoa.buscar(cpf, pacientes);

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

        List<Paciente> pacientes = sistema.getPacientes();
   
        System.out.println("Digite o CPF do paciente");
        String cpf = scanner.nextLine();
        System.out.println("Digite a descrição do tratamento:");
        String descricao = scanner.nextLine();
        System.out.println("Digite a duração em dias:");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Paciente pacienteEncontrado =  (Paciente) Pessoa.buscar(cpf, pacientes);

        if (pacienteEncontrado != null) {
        System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
        } else {
        System.out.println("Paciente não encontrado.");
        }

        Tratamento tratamento = new Tratamento(descricao, duracao, pacienteEncontrado);
        prescricao.add(tratamento);
        System.out.println("Tratamento prescrito com sucesso!");
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
                case 1 -> Atualizar();
                case 2 -> Excluir();
                case 3 -> Buscar();
                case 4 -> exibirMenu();
                default -> System.out.println("Opção inválida.");
            }
        }

    }

    private void Buscar(){
        List<Paciente> pacientes = sistema.getPacientes();
        List<Medico> medicos = sistema.getMedicos();
        
            System.out.println("Digite o cpf: ");
            String cpf = scanner.nextLine();
            
            Paciente pacienteEncontrado =  (Paciente) Pessoa.buscar(cpf, pacientes);

        if (pacienteEncontrado != null) {
        System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
        } 
        
        else {
            Medico medicoEncontrado =  (Medico) Pessoa.buscar(cpf, medicos);

            if (medicoEncontrado != null) {
            System.out.println("Medico encontrado: " + medicoEncontrado.getNome());
            } else {
            System.out.println("Pessoa não encontrada.");
            }
        }

        }

    

    private void Atualizar(){

        List<Paciente> pacientes = sistema.getPacientes();
        List<Medico> medicos = sistema.getMedicos();

        System.out.println("Digite o cpf: ");
        String cpf = scanner.nextLine();

        Paciente pacienteEncontrado =  (Paciente) Pessoa.buscar(cpf, pacientes);

        if (pacienteEncontrado != null) {
        System.out.println("Paciente encontrado: " + pacienteEncontrado.getNome());
        System.out.println("Novo nome: ");
        String name = scanner.nextLine();
        System.out.println("Novo cpf: ");
        String novoCPF= scanner.nextLine();
        System.out.print("Data de nascimento (YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        pacienteEncontrado.atualizar(name, novoCPF, dataNascimento);
        } 
        
        else {
            Medico medicoEncontrado =  (Medico) Pessoa.buscar(cpf, medicos);

            if (medicoEncontrado != null) {
            System.out.println("Medico encontrado: " + medicoEncontrado.getNome());
            System.out.println("Novo nome: ");
            String name = scanner.nextLine();
            System.out.println("Novo cpf: ");
            String novoCPF= scanner.nextLine();
            System.out.print("Data de nascimento (YYYY-MM-DD): ");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
            medicoEncontrado.atualizar(name, novoCPF, dataNascimento);
            } else {
            System.out.println("Pessoa não encontrada.");
            }
        }

        
    }

    private void Excluir(){

        List<Paciente> pacientes = sistema.getPacientes();
        List<Medico> medicos = sistema.getMedicos();

        System.out.println("Digite o cpf: ");
        String cpf = scanner.nextLine();

        boolean resultado =  Pessoa.deletar(cpf, pacientes);

        if (resultado) {
        System.out.println("Paciente excluido!");
        } 
        
        else {
            boolean resultado2 =  Pessoa.deletar(cpf, medicos);

            if (resultado2) {
            System.out.println("Medico excluido!");
            } else {
            System.out.println("Pessoa não encontrada.");
            }
        }


        
    }



}

   




  