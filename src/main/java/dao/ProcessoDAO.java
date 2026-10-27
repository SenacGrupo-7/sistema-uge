package dao;

import connection.Conexao;
import model.Processo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessoDAO {

    public void inserir(Processo processo) {

        String sql = """
            INSERT INTO processos(
                numero_processo,
                situacao,
                data_cadastro
            )
            VALUES(
                ?,
                ?,
                CURRENT_TIMESTAMP
            )
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1,
                    processo.getNumeroProcesso());

            ps.setString(2,
                    processo.getSituacao());

            ps.executeUpdate();

            System.out.println(
                    "Processo cadastrado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Processo buscarPorId(Long id) {

        String sql = """
            SELECT *
            FROM processos
            WHERE id_processo = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Processo p =
                        new Processo();

                p.setIdProcesso(
                        rs.getLong("id_processo"));

                p.setNumeroProcesso(
                        rs.getString("numero_processo"));

                p.setSituacao(
                        rs.getString("situacao"));

                Timestamp ts =
                        rs.getTimestamp(
                                "data_cadastro");

                if (ts != null) {
                    p.setDataCadastro(
                            ts.toLocalDateTime());
                }

                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Processo buscarPorNumero(
            String numeroProcesso) {

        String sql = """
            SELECT *
            FROM processos
            WHERE numero_processo = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1,
                    numeroProcesso);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Processo p =
                        new Processo();

                p.setIdProcesso(
                        rs.getLong("id_processo"));

                p.setNumeroProcesso(
                        rs.getString(
                                "numero_processo"));

                p.setSituacao(
                        rs.getString(
                                "situacao"));

                Timestamp ts =
                        rs.getTimestamp(
                                "data_cadastro");

                if (ts != null) {

                    p.setDataCadastro(
                            ts.toLocalDateTime());
                }

                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Processo> listarTodos() {

        List<Processo> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM processos
            ORDER BY numero_processo
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                Processo p =
                        new Processo();

                p.setIdProcesso(
                        rs.getLong("id_processo"));

                p.setNumeroProcesso(
                        rs.getString(
                                "numero_processo"));

                p.setSituacao(
                        rs.getString(
                                "situacao"));

                Timestamp ts =
                        rs.getTimestamp(
                                "data_cadastro");

                if (ts != null) {

                    p.setDataCadastro(
                            ts.toLocalDateTime());
                }

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Processo> pesquisar(
            String filtro) {

        List<Processo> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM processos
                WHERE UPPER(numero_processo)
                LIKE UPPER(?)
                ORDER BY numero_processo
                """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + filtro + "%");

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Processo p =
                        new Processo();

                p.setIdProcesso(
                        rs.getLong("id_processo"));

                p.setNumeroProcesso(
                        rs.getString(
                                "numero_processo"));

                p.setSituacao(
                        rs.getString(
                                "situacao"));

                Timestamp ts =
                        rs.getTimestamp(
                                "data_cadastro");

                if (ts != null) {

                    p.setDataCadastro(
                            ts.toLocalDateTime());
                }

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(Processo processo) {

        String sql = """
            UPDATE processos
            SET
                numero_processo = ?,
                situacao = ?
            WHERE id_processo = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    processo.getNumeroProcesso());

            ps.setString(
                    2,
                    processo.getSituacao());

            ps.setLong(
                    3,
                    processo.getIdProcesso());

            int linhas =
                    ps.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "Processo atualizado.");
            } else {

                System.out.println(
                        "Processo não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean possuiDiarias(
            Long idProcesso) {

        String sql = """
            SELECT COUNT(*)
            FROM diarias
            WHERE id_processo = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idProcesso);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean possuiPagamentos(
            Long idProcesso) {

        String sql = """
            SELECT COUNT(*)
            FROM pagamentos_processo
            WHERE id_processo = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idProcesso);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void excluir(Long idProcesso) {

        if (possuiDiarias(idProcesso)) {

            System.out.println(
                    "Não é possível excluir. Processo possui diárias vinculadas.");

            return;
        }

        if (possuiPagamentos(idProcesso)) {

            System.out.println(
                    "Não é possível excluir. Processo possui pagamentos vinculados.");

            return;
        }

        String sql = """
            DELETE
            FROM processos
            WHERE id_processo = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idProcesso);

            int linhas =
                    ps.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "Processo excluído.");
            } else {

                System.out.println(
                        "Processo não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}