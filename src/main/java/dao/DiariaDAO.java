package dao;

import connection.Conexao;
import model.Diaria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiariaDAO {

    public void inserir(Diaria diaria) {

        String sql = """
            INSERT INTO diarias(
                cpf_servidor,
                id_tipo,
                data_inicial,
                destino,
                motivo,
                valor_diaria,
                id_processo,
                observacao
            )
            VALUES(?,?,?,?,?,?,?,?)
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, diaria.getCpfServidor());

            ps.setShort(2, diaria.getIdTipo());

            ps.setDate(
                    3,
                    Date.valueOf(
                            diaria.getDataInicial()));

            ps.setString(
                    4,
                    diaria.getDestino());

            ps.setString(
                    5,
                    diaria.getMotivo());

            ps.setBigDecimal(
                    6,
                    diaria.getValorDiaria());

            ps.setLong(
                    7,
                    diaria.getIdProcesso());

            ps.setString(
                    8,
                    diaria.getObservacao());

            ps.executeUpdate();

            System.out.println(
                    "Diária cadastrada com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Diaria buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM diarias
                WHERE id_diaria = ?
                """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return montarDiaria(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Diaria> listarTodas() {

        List<Diaria> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM diarias
                ORDER BY data_inicial DESC
                """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                lista.add(
                        montarDiaria(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Diaria> listarPorCpf(
            String cpf) {

        List<Diaria> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM diarias
            WHERE cpf_servidor = ?
            ORDER BY data_inicial DESC
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, cpf);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(
                        montarDiaria(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Diaria> listarPorProcesso(
            Long idProcesso) {

        List<Diaria> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM diarias
            WHERE id_processo = ?
            ORDER BY data_inicial DESC
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idProcesso);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(
                        montarDiaria(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Diaria> listarPorPeriodo(
            Date inicio,
            Date fim) {

        List<Diaria> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM diarias
                WHERE data_inicial
                BETWEEN ? AND ?
                ORDER BY data_inicial
                """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setDate(1, inicio);
            ps.setDate(2, fim);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(
                        montarDiaria(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(
            Diaria diaria) {

        String sql = """
                UPDATE diarias
                SET
                    cpf_servidor = ?,
                    id_tipo = ?,
                    data_inicial = ?,
                    destino = ?,
                    motivo = ?,
                    valor_diaria = ?,
                    id_processo = ?,
                    observacao = ?
                WHERE id_diaria = ?
                """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    diaria.getCpfServidor());

            ps.setShort(
                    2,
                    diaria.getIdTipo());

            ps.setDate(
                    3,
                    Date.valueOf(
                            diaria.getDataInicial()));

            ps.setString(
                    4,
                    diaria.getDestino());

            ps.setString(
                    5,
                    diaria.getMotivo());

            ps.setBigDecimal(
                    6,
                    diaria.getValorDiaria());

            ps.setLong(
                    7,
                    diaria.getIdProcesso());

            ps.setString(
                    8,
                    diaria.getObservacao());

            ps.setLong(
                    9,
                    diaria.getIdDiaria());

            int linhas =
                    ps.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "Diária atualizada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void vincularPD(
            Long idDiaria,
            String numeroPD) {

        String sql = """
            UPDATE diarias
            SET numero_pd = ?
            WHERE id_diaria = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, numeroPD);

            ps.setLong(2, idDiaria);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(
            Long idDiaria) {

        String sql = """
            DELETE
            FROM diarias
            WHERE id_diaria = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idDiaria);

            int linhas =
                    ps.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "Diária excluída.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Diaria montarDiaria(
            ResultSet rs)
            throws SQLException {

        Diaria d = new Diaria();

        d.setIdDiaria(
                rs.getLong("id_diaria"));

        d.setCpfServidor(
                rs.getString(
                        "cpf_servidor"));

        d.setIdTipo(
                rs.getShort(
                        "id_tipo"));

        if (rs.getDate(
                "data_inicial") != null) {

            d.setDataInicial(
                    rs.getDate(
                                    "data_inicial")
                            .toLocalDate());
        }

        d.setDestino(
                rs.getString(
                        "destino"));

        d.setMotivo(
                rs.getString(
                        "motivo"));

        d.setValorDiaria(
                rs.getBigDecimal(
                        "valor_diaria"));

        d.setIdProcesso(
                rs.getLong(
                        "id_processo"));

        d.setObservacao(
                rs.getString(
                        "observacao"));

        d.setNumeroPd(
                rs.getString(
                        "numero_pd"));

        return d;
    }

    public Long buscarIdProcesso(
            String numeroProcesso) {

        String sql = """
        SELECT id_processo
        FROM processos
        WHERE numero_processo = ?
        """;

        try (
                Connection conn =
                        Conexao.conectar();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    numeroProcesso);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getLong(
                        "id_processo");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

}