package dao;

import connection.Conexao;
import model.Pagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void inserir(Pagamento pagamento) {

        String sql = """
            INSERT INTO pagamentos_processo(
                id_processo,
                numero_pd,
                data_pagamento,
                valor_pagamento,
                id_pd
            )
            VALUES(?,?,?,?,?)
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(
                    1,
                    pagamento.getIdProcesso());

            ps.setString(
                    2,
                    pagamento.getNumeroPd());

            ps.setDate(
                    3,
                    Date.valueOf(
                            pagamento.getDataPagamento()));

            ps.setBigDecimal(
                    4,
                    pagamento.getValorPagamento());

            ps.setLong(
                    5,
                    pagamento.getIdPd());

            ps.executeUpdate();

            System.out.println(
                    "Pagamento cadastrado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pagamento buscarPorId(Long idPagamento) {

        String sql = """
            SELECT *
            FROM pagamentos_processo
            WHERE id_pagamento = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPagamento);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return montarPagamento(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Pagamento> listarTodos() {

        List<Pagamento> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM pagamentos_processo
            ORDER BY data_pagamento DESC
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
                        montarPagamento(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Pagamento> listarPorProcesso(
            Long idProcesso) {

        List<Pagamento> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM pagamentos_processo
            WHERE id_processo = ?
            ORDER BY data_pagamento DESC
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
                        montarPagamento(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Pagamento> listarPorPD(
            String numeroPd) {

        List<Pagamento> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM pagamentos_processo
            WHERE numero_pd = ?
            ORDER BY data_pagamento DESC
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, numeroPd);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(
                        montarPagamento(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(
            Pagamento pagamento) {

        String sql = """
            UPDATE pagamentos_processo
            SET
                numero_pd = ?,
                data_pagamento = ?,
                valor_pagamento = ?,
                id_pd = ?
            WHERE id_pagamento = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    pagamento.getNumeroPd());

            ps.setDate(
                    2,
                    Date.valueOf(
                            pagamento.getDataPagamento()));

            ps.setBigDecimal(
                    3,
                    pagamento.getValorPagamento());

            ps.setLong(
                    4,
                    pagamento.getIdPd());

            ps.setLong(
                    5,
                    pagamento.getIdPagamento());

            int linhas =
                    ps.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "Pagamento atualizado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long idPagamento) {

        String sql = """
            DELETE
            FROM pagamentos_processo
            WHERE id_pagamento = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPagamento);

            int linhas =
                    ps.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "Pagamento excluído.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Pagamento montarPagamento(
            ResultSet rs)
            throws SQLException {

        Pagamento p =
                new Pagamento();

        p.setIdPagamento(
                rs.getLong(
                        "id_pagamento"));

        p.setIdProcesso(
                rs.getLong(
                        "id_processo"));

        p.setNumeroPd(
                rs.getString(
                        "numero_pd"));

        if (rs.getDate(
                "data_pagamento") != null) {

            p.setDataPagamento(
                    rs.getDate(
                                    "data_pagamento")
                            .toLocalDate());
        }

        p.setValorPagamento(
                rs.getBigDecimal(
                        "valor_pagamento"));

        p.setIdPd(
                rs.getLong(
                        "id_pd"));

        return p;
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

    public Long buscarIdPD(
            String numeroPD) {

        String sql = """
            SELECT id_pd
            FROM pds
            WHERE numero_pd = ?
            """;

        try (
                Connection conn =
                        Conexao.conectar();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    numeroPD);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getLong(
                        "id_pd");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}
