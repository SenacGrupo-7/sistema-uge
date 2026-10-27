package dao;

import connection.Conexao;
import model.PD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PDDAO {

    public void inserir(PD pd) {

        validarPD(pd);

        String sql = """
            INSERT INTO pds(
                numero_pd,
                data_pagamento,
                valor_total
            )
            VALUES(?,?,?)
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    pd.getNumeroPd().trim());

            if (pd.getDataPagamento() != null) {

                ps.setDate(
                        2,
                        Date.valueOf(
                                pd.getDataPagamento()));

            } else {

                ps.setNull(
                        2,
                        Types.DATE);
            }

            if (pd.getValorTotal() != null) {

                ps.setBigDecimal(
                        3,
                        pd.getValorTotal());

            } else {

                ps.setNull(
                        3,
                        Types.NUMERIC);
            }

            ps.executeUpdate();

            System.out.println(
                    "PD cadastrada com sucesso.");

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao inserir PD: "
                            + e.getMessage(),
                    e);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public PD buscarPorId(Long idPd) {

        if (idPd == null) {
            return null;
        }

        String sql = """
            SELECT *
            FROM pds
            WHERE id_pd = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPd);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next()
                    ? montarPD(rs)
                    : null;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao buscar PD.",
                    e);
        }
    }

    public PD buscarPorNumero(
            String numeroPd) {

        if (numeroPd == null
                || numeroPd.isBlank()) {

            return null;
        }

        String sql = """
            SELECT *
            FROM pds
            WHERE numero_pd = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    numeroPd.trim());

            ResultSet rs =
                    ps.executeQuery();

            return rs.next()
                    ? montarPD(rs)
                    : null;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao buscar PD.",
                    e);
        }
    }

    public List<PD> listarTodas() {

        List<PD> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM pds
            ORDER BY numero_pd
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
                        montarPD(rs));
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao listar PDs.",
                    e);
        }

        return lista;
    }

    public boolean existePD(
            String numeroPd) {

        if (numeroPd == null
                || numeroPd.isBlank()) {

            return false;
        }

        String sql = """
            SELECT EXISTS(
                SELECT 1
                FROM pds
                WHERE numero_pd = ?
            )
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    numeroPd.trim());

            ResultSet rs =
                    ps.executeQuery();

            return rs.next()
                    && rs.getBoolean(1);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao verificar PD.",
                    e);
        }
    }

    public void atualizar(PD pd) {

        validarPD(pd);

        if (pd.getIdPd() == null) {

            throw new IllegalArgumentException(
                    "ID da PD não informado.");
        }

        String sql = """
            UPDATE pds
            SET
                numero_pd = ?,
                data_pagamento = ?,
                valor_total = ?
            WHERE id_pd = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    pd.getNumeroPd().trim());

            if (pd.getDataPagamento() != null) {

                ps.setDate(
                        2,
                        Date.valueOf(
                                pd.getDataPagamento()));

            } else {

                ps.setNull(
                        2,
                        Types.DATE);
            }

            if (pd.getValorTotal() != null) {

                ps.setBigDecimal(
                        3,
                        pd.getValorTotal());

            } else {

                ps.setNull(
                        3,
                        Types.NUMERIC);
            }

            ps.setLong(
                    4,
                    pd.getIdPd());

            ps.executeUpdate();

            System.out.println(
                    "PD atualizada com sucesso.");

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao atualizar PD.",
                    e);
        }
    }

    public boolean possuiPagamento(
            Long idPd) {

        if (idPd == null) {

            return false;
        }

        String sql = """
            SELECT COUNT(*)
            FROM pagamentos_processo
            WHERE id_pd = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPd);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao verificar vínculos.",
                    e);
        }

        return false;
    }

    public void excluir(Long idPd) {

        if (idPd == null) {

            throw new IllegalArgumentException(
                    "ID da PD inválido.");
        }

        if (possuiPagamento(idPd)) {

            throw new RuntimeException(
                    "Não é possível excluir. Existem pagamentos vinculados.");
        }

        String sql = """
            DELETE
            FROM pds
            WHERE id_pd = ?
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPd);

            ps.executeUpdate();

            System.out.println(
                    "PD excluída com sucesso.");

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao excluir PD.",
                    e);
        }
    }

    public Long obterOuCriarPD(
            String numeroPd) {

        if (numeroPd == null
                || numeroPd.isBlank()) {

            throw new IllegalArgumentException(
                    "Número da PD obrigatório.");
        }

        PD pdExistente =
                buscarPorNumero(numeroPd);

        if (pdExistente != null) {

            return pdExistente.getIdPd();
        }

        String sql = """
            INSERT INTO pds(
                numero_pd
            )
            VALUES(?)
            RETURNING id_pd
            """;

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    numeroPd.trim());

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getLong(
                        "id_pd");
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao criar PD.",
                    e);
        }

        return null;
    }

    private PD montarPD(ResultSet rs)
            throws SQLException {

        PD pd = new PD();

        pd.setIdPd(
                rs.getLong(
                        "id_pd"));

        pd.setNumeroPd(
                rs.getString(
                        "numero_pd"));

        Date data =
                rs.getDate(
                        "data_pagamento");

        if (data != null) {

            pd.setDataPagamento(
                    data.toLocalDate());
        }

        pd.setValorTotal(
                rs.getBigDecimal(
                        "valor_total"));

        return pd;
    }

    private void validarPD(PD pd) {

        if (pd == null) {

            throw new IllegalArgumentException(
                    "PD não informada.");
        }

        if (pd.getNumeroPd() == null
                || pd.getNumeroPd().isBlank()) {

            throw new IllegalArgumentException(
                    "Número da PD obrigatório.");
        }
    }
}