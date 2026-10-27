package service;

import dao.PagamentoDAO;
import model.Pagamento;

import java.util.List;

public class PagamentoService {

    private final PagamentoDAO dao =
            new PagamentoDAO();

    public void cadastrar(
            Pagamento pagamento) {

        if (pagamento == null) {

            throw new IllegalArgumentException(
                    "Pagamento não informado.");
        }

        if (pagamento.getIdProcesso() == null) {

            throw new IllegalArgumentException(
                    "Processo obrigatório.");
        }

        if (pagamento.getNumeroPd() == null
                || pagamento.getNumeroPd().isBlank()) {

            throw new IllegalArgumentException(
                    "PD obrigatória.");
        }

        dao.inserir(pagamento);
    }

    public Pagamento buscarPorId(
            Long id) {

        return dao.buscarPorId(id);
    }

    public List<Pagamento> listarTodos() {

        return dao.listarTodos();
    }

    public List<Pagamento> listarPorProcesso(
            Long idProcesso) {

        return dao.listarPorProcesso(
                idProcesso);
    }

    public List<Pagamento> listarPorPD(
            String numeroPd) {

        return dao.listarPorPD(
                numeroPd);
    }

    public void atualizar(
            Pagamento pagamento) {

        dao.atualizar(pagamento);
    }

    public void excluir(
            Long id) {

        dao.excluir(id);
    }
}
