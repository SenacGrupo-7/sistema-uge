package service;

import dao.ServidorDAO;
import model.Servidor;

import java.util.List;

public class ServidorService {

    private final ServidorDAO dao =
            new ServidorDAO();

    public void cadastrar(Servidor servidor) {

        validarServidor(servidor);

        if (dao.buscarPorCpf(
                servidor.getCpfServidor()) != null) {

            throw new RuntimeException(
                    "Servidor já cadastrado.");
        }

        dao.inserir(servidor);
    }

    public Servidor buscarPorCpf(String cpf) {

        return dao.buscarPorCpf(cpf);
    }

    public List<Servidor> buscarPorNome(
            String nome) {

        return dao.buscarPorNome(nome);
    }

    public List<Servidor> listarTodos() {

        return dao.listarTodos();
    }

    public void atualizar(
            Servidor servidor) {

        validarServidor(servidor);

        dao.atualizar(servidor);
    }

    public void excluir(String cpf) {

        dao.excluir(cpf);
    }

    private void validarServidor(
            Servidor servidor) {

        if (servidor == null)
            throw new IllegalArgumentException(
                    "Servidor não informado.");

        if (servidor.getCpfServidor() == null
                || servidor.getCpfServidor().isBlank())
            throw new IllegalArgumentException(
                    "CPF obrigatório.");

        if (servidor.getNomeServidor() == null
                || servidor.getNomeServidor().isBlank())
            throw new IllegalArgumentException(
                    "Nome obrigatório.");

        if (servidor.getIdCargo() == null)
            throw new IllegalArgumentException(
                    "Cargo obrigatório.");
    }
}