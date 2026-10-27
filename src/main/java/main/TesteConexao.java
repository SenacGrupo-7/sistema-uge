package main;

import connection.Conexao;
import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection conn = Conexao.conectar()) {

            System.out.println("--------------------------------");
            System.out.println("CONEXÃO REALIZADA COM SUCESSO");
            System.out.println("--------------------------------");

        } catch (Exception e) {

            System.out.println("ERRO AO CONECTAR");
            e.printStackTrace();
        }
    }
}