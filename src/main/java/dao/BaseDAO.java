package dao;

import connection.Conexao;
import java.sql.Connection;

public abstract class BaseDAO {

    protected Connection getConnection()
            throws Exception {

        return Conexao.conectar();
    }
}