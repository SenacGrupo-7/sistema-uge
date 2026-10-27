package dao;

import connection.Conexao;
import model.Servidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorDAO  extends BaseDAO{

    public void inserir(Servidor servidor) {

        String sql = """
            INSERT INTO servidores(
                cpf_servidor,
                nome_servidor,
                id_cargo,
                ativo
            )
            VALUES(?,?,?,?)
            """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, servidor.getCpfServidor());
            ps.setString(2, servidor.getNomeServidor());
            ps.setShort(3, servidor.getIdCargo());
            ps.setBoolean(4, servidor.getAtivo());

            ps.executeUpdate();

            System.out.println("Servidor cadastrado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Servidor buscarPorCpf(String cpf) {

        String sql = """
    SELECT
        s.*,
        c.nome_cargo
    FROM servidores s
    LEFT JOIN cargo c
        ON c.id_cargo = s.id_cargo
    WHERE s.cpf_servidor = ?
    """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, cpf);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return montarServidor(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Servidor> buscarPorNome(
            String nome) {

        List<Servidor> lista =
                new ArrayList<>();

        String sql = """
        SELECT
            s.*,
            c.nome_cargo
        FROM servidores s
        LEFT JOIN cargo c
            ON c.id_cargo = s.id_cargo
        WHERE UPPER(s.nome_servidor)
              LIKE UPPER(?)
        ORDER BY s.nome_servidor
        """;

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + nome + "%");

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(
                        montarServidor(rs));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public List<Servidor> listarTodos() {

        List<Servidor> lista =
                new ArrayList<>();

        String sql = """
        SELECT
            s.*,
            c.nome_cargo
        FROM servidores s
        LEFT JOIN cargo c
            ON c.id_cargo = s.id_cargo
        ORDER BY s.nome_servidor
        """;

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                lista.add(
                        montarServidor(rs));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(Servidor servidor) {

        String sql = """
            UPDATE servidores
            SET
                nome_servidor = ?,
                id_cargo = ?,
                ativo = ?
            WHERE cpf_servidor = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1,
                    servidor.getNomeServidor());

            ps.setShort(2,
                    servidor.getIdCargo());

            ps.setBoolean(3,
                    servidor.getAtivo());

            ps.setString(4,
                    servidor.getCpfServidor());

            ps.executeUpdate();

            System.out.println("Servidor atualizado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(String cpf) {

        String sql = """
            DELETE FROM servidores
            WHERE cpf_servidor = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, cpf);

            ps.executeUpdate();

            System.out.println("Servidor excluído.");

        } catch (Exception e) {

            System.out.println(
                    "Servidor possui vínculos.");

            e.printStackTrace();
        }
    }

    private Servidor montarServidor(
            ResultSet rs)
            throws SQLException {

        Servidor s = new Servidor();

        s.setCpfServidor(
                rs.getString("cpf_servidor"));

        s.setNomeServidor(
                rs.getString("nome_servidor"));

        s.setIdCargo(
                rs.getShort("id_cargo"));

        s.setAtivo(
                rs.getBoolean("ativo"));

        try {

            s.setNomeCargo(
                    rs.getString("nome_cargo"));

        } catch (Exception ignored) {
        }

        return s;
    }



}