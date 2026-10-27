package service;

import dao.PDDAO;
import model.PD;

import java.util.List;

public class PDService {

    private final PDDAO dao =
            new PDDAO();

    public void cadastrar(
            PD pd) {

        dao.inserir(pd);
    }

    public PD buscarPorId(
            Long id) {

        return dao.buscarPorId(id);
    }

    public PD buscarPorNumero(
            String numeroPd) {

        return dao.buscarPorNumero(numeroPd);
    }

    public List<PD> listarTodas() {

        return dao.listarTodas();
    }

    public void atualizar(
            PD pd) {

        dao.atualizar(pd);
    }

    public void excluir(
            Long id) {

        dao.excluir(id);
    }

    public Long obterOuCriarPD(
            String numeroPd) {

        return dao.obterOuCriarPD(
                numeroPd);
    }
}
