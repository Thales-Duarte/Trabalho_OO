package main;

import main.controller.*;


import main.services.*;
import main.menu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaGerenciamentoClinica sistema = new SistemaGerenciamentoClinica();
        List<Consulta> consultasExistentes = new ArrayList<>();
        List<Exame> exames = new ArrayList<>();

        Menu menuCliente = new MenuCliente(scanner, consultasExistentes, exames, sistema);
        Menu menuFuncionario = new MenuFuncionario(scanner, sistema);

        while (true) {
            System.out.println("\n=== Bem-vindo ao Sistema de Gerenciamento de Clínica ===");
            System.out.println("1. Entrar como Cliente (Paciente)");
            System.out.println("2. Entrar como Funcionário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcaoInicial = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoInicial) {
                case 1 -> menuCliente.exibirMenu();
                case 2 -> menuFuncionario.exibirMenu();
                case 3 -> {
                    System.out.println("Encerrando o sistema. Até logo!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

    