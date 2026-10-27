package menu;

import model.Servidor;
import service.ServidorService;

import java.util.List;
import java.util.Scanner;

public class MenuServidor {

    private final Scanner scanner =
            new Scanner(System.in);

    private final ServidorService service =
            new ServidorService();

    public void iniciar() {

        int opcao;

        do {

            System.out.println("\n=== SERVIDORES ===");

            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar CPF");
            System.out.println("3 - Listar");
            System.out.println("4 - Alterar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");

            opcao =
                    Integer.parseInt(
                            scanner.nextLine());

            switch (opcao) {

                case 1 -> cadastrar();

                case 2 -> consultar();

                case 3 -> listar();

                case 4 -> alterar();

                case 5 -> excluir();
            }

        } while (opcao != 0);
    }

    private void cadastrar() {

        try {

            Servidor servidor =
                    new Servidor();

            System.out.print("CPF: ");
            servidor.setCpfServidor(
                    scanner.nextLine());

            System.out.print("Nome: ");
            servidor.setNomeServidor(
                    scanner.nextLine());

            System.out.print("Cargo: ");
            servidor.setIdCargo(
                    Short.parseShort(
                            scanner.nextLine()));

            servidor.setAtivo(true);

            service.cadastrar(servidor);

            System.out.println(
                    "\nServidor cadastrado.");

        } catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }

    private void consultar() {

        try {

            System.out.print("CPF: ");

            String cpf =
                    scanner.nextLine();

            Servidor servidor =
                    service.buscarPorCpf(cpf);

            if (servidor == null) {

                System.out.println(
                        "\nServidor não encontrado.");

                return;
            }

            System.out.println(
                    "\n=== DADOS DO SERVIDOR ===");

            System.out.println(
                    "CPF: "
                            + servidor.getCpfServidor());

            System.out.println(
                    "Nome: "
                            + servidor.getNomeServidor());

            System.out.println(
                    "Cargo: "
                            + servidor.getNomeCargo());

            System.out.println(
                    "Ativo: "
                            + servidor.getAtivo());

        } catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }

    private void listar() {

        try {

            List<Servidor> lista =
                    service.listarTodos();

            if (lista.isEmpty()) {

                System.out.println(
                        "\nNenhum servidor encontrado.");

                return;
            }

            for (Servidor servidor : lista) {

                System.out.println(
                        servidor.getCpfServidor()
                                + " - "
                                + servidor.getNomeServidor());
            }

        } catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }

    private void alterar() {

        System.out.println(
                "\nFuncionalidade em implementação.");
    }

    private void excluir() {

        try {

            System.out.print(
                    "CPF do servidor: ");

            String cpf =
                    scanner.nextLine();

            service.excluir(cpf);

        } catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }
}