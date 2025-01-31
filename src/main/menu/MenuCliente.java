package main.menu;

import main.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import main.services.*;

public class MenuCliente implements Menu {
    private final Scanner scanner;
    private final List<Paciente> pacientes;
    private final List<Consulta> consultas;
    private final List<Exame> exames;

    public MenuCliente(Scanner scanner, List<Paciente> pacientes, List<Consulta> consultas, List<Exame> exames) {
        this.scanner = scanner;
        this.pacientes = pacientes;
        this.consultas = consultas;
        this.exames = exames;
    }
    
    @Override
    public void exibirMenu() {
        System.out.println("\n=== Área do Cliente ===");
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        System.out.println("Selecione seu nome: ");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNome());
        }
        int pacienteIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        Paciente paciente = pacientes.get(pacienteIndex);

        exibirNotificacoes(paciente);
    }

    private void exibirNotificacoes(Paciente paciente) {
        LocalDate hoje = LocalDate.now();
        System.out.println("\n=== Notificações ===");

        boolean temNotificacoes = false;
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(paciente) &&
                !consulta.getDataConsulta().isBefore(hoje) &&
                consulta.getDataConsulta().isBefore(hoje.plusDays(3))) {
                System.out.println("Lembrete: Você tem uma consulta com " + consulta.getMedico().getNome() +
                        " no dia " + consulta.getDataConsulta());
                temNotificacoes = true;
            }
        }

        for (Exame exame : exames) {
            if (exame.getConsulta().getPaciente().equals(paciente) &&
                !exame.getDataValidade().isBefore(hoje) &&
                exame.getDataValidade().isBefore(hoje.plusDays(3))) {
                System.out.println("Lembrete: Seu exame de " + exame.getTipo() +
                        " deve ser realizado até " + exame.getDataValidade());
                temNotificacoes = true;
            }
        }

        

        if (!temNotificacoes) {
            System.out.println("Nenhuma notificação no momento.");
        }

    }
}
