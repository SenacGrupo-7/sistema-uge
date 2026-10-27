package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/uge";

    private static final String USER = "postgres";
    private static final String PASS = "Idy5i5t4@";

    public static Connection conectar() throws Exception {

        return DriverManager.getConnection(
                URL,
                USER,
                PASS
        );
    }


}