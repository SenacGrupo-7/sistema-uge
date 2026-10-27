package org.example;

import connection.Conexao;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = Conexao.conectar()) {

            System.out.println("✅ Conectado ao PostgreSQL");
            System.out.println("Banco: " + conn.getCatalog());

        } catch (Exception e) {

            System.out.println("❌ Erro de conexão");
            e.printStackTrace();

        }
    }
}