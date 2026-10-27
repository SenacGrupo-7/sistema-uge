package service;

import dao.ProcessoDAO;
import model.Processo;

import java.util.List;

public class ProcessoService {

    private final ProcessoDAO dao =
            new ProcessoDAO();

    public void cadastrar(
            Processo processo) {

        if (processo == null) {

            throw new IllegalArgumentException(
                    "Processo não informado.");
        }

        if (processo.getNumeroProcesso() == null
                || processo.getNumeroProcesso().isBlank()) {

            throw new IllegalArgumentException(
                    "Número do processo obrigatório.");
        }

        dao.inserir(processo);
    }

    public Processo buscarPorId(
            Long id) {

        return dao.buscarPorId(id);
    }

    public Processo buscarPorNumero(
            String numero) {

        return dao.buscarPorNumero(numero);
    }

    public List<Processo> listarTodos() {

        return dao.listarTodos();
    }

    public List<Processo> pesquisar(
            String filtro) {

        return dao.pesquisar(filtro);
    }

    public void atualizar(
            Processo processo) {

        dao.atualizar(processo);
    }

    public void excluir(
            Long id) {

        dao.excluir(id);
    }
}
