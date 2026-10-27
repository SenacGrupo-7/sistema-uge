package menu;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner =
            new Scanner(System.in);

    public void iniciar() {

        int opcao;

        do {

            exibirMenu();

            try {

                opcao =
                        Integer.parseInt(
                                scanner.nextLine());

                switch (opcao) {

                    case 1 -> new MenuServidor().iniciar();

                    case 2 -> new MenuProcesso().iniciar();

                    case 3 -> new MenuDiaria().iniciar();

                    case 4 -> new MenuPagamento().iniciar();

                    case 5 -> new MenuConsulta().consultarCPF();

                    case 6 -> new MenuConsulta().consultarNome();

                    case 7 -> new MenuConsulta().consultarProcesso();

                    case 8 -> new MenuConsulta().consultarPD();

                    case 9 -> new MenuConsulta().consultarTransparencia();

                    case 0 -> System.out.println(
                            "\nSistema encerrado.");

                    default -> System.out.println(
                            "\nOpção inválida.");

                }

            } catch (Exception e) {

                opcao = -1;

                System.out.println(
                        "\nInforme apenas números.");
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {

        System.out.println();
        System.out.println(
                "========================================");

        System.out.println(
                "      SISTEMA DE DIÁRIAS - UGE");

        System.out.println(
                "========================================");

        System.out.println(
                "1  - Servidores");

        System.out.println(
                "2  - Processos");

        System.out.println(
                "3  - Diárias");

        System.out.println(
                "4  - Pagamentos");

        System.out.println(
                "5  - Consulta CPF");

        System.out.println(
                "6  - Consulta Nome");

        System.out.println(
                "7  - Consulta Processo");

        System.out.println(
                "8  - Consulta PD");

        System.out.println(
                "9  - Transparência");

        System.out.println(
                "0  - Sair");

        System.out.println(
                "========================================");

        System.out.print(
                "Escolha uma opção: ");
    }
}