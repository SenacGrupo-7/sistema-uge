package menu;

import java.util.Scanner;

public class MenuDiaria {

    private final Scanner scanner =
            new Scanner(System.in);

    public void iniciar() {

        int opcao;

        do {

            System.out.println(
                    "\n=== DIÁRIAS ===");

            System.out.println(
                    "1 - Cadastrar");

            System.out.println(
                    "2 - Consultar Processo");

            System.out.println(
                    "3 - Consultar CPF");

            System.out.println(
                    "4 - Alterar");

            System.out.println(
                    "5 - Excluir");

            System.out.println(
                    "0 - Voltar");

            System.out.print(
                    "Opção: ");

            opcao =
                    Integer.parseInt(
                            scanner.nextLine());

        } while (opcao != 0);
    }
}
