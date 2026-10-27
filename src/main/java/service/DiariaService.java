package service;

import dao.DiariaDAO;
import model.Diaria;

import java.sql.Date;
import java.util.List;

public class DiariaService {

    private final DiariaDAO dao =
            new DiariaDAO();

    public void cadastrar(
            Diaria diaria) {

        if (diaria == null) {

            throw new IllegalArgumentException(
                    "Diária não informada.");
        }

        if (diaria.getCpfServidor() == null
                || diaria.getCpfServidor().isBlank()) {

            throw new IllegalArgumentException(
                    "Servidor obrigatório.");
        }

        if (diaria.getIdProcesso() == null) {

            throw new IllegalArgumentException(
                    "Processo obrigatório.");
        }

        dao.inserir(diaria);
    }

    public Diaria buscarPorId(
            Long id) {

        return dao.buscarPorId(id);
    }

    public List<Diaria> listarTodas() {

        return dao.listarTodas();
    }

    public List<Diaria> listarPorCpf(
            String cpf) {

        return dao.listarPorCpf(cpf);
    }

    public List<Diaria> listarPorProcesso(
            Long idProcesso) {

        return dao.listarPorProcesso(
                idProcesso);
    }

    public List<Diaria> listarPorPeriodo(
            Date inicio,
            Date fim) {

        return dao.listarPorPeriodo(
                inicio,
                fim);
    }

    public void atualizar(
            Diaria diaria) {

        dao.atualizar(diaria);
    }

    public void excluir(
            Long id) {

        dao.excluir(id);
    }
}
