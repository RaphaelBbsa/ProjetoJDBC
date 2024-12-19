package br.com.rporto.dao;

import br.com.rporto.domain.Cliente;

import java.util.List;

public interface IClienteDAO {
    void salvar(Cliente cliente);
    void atualizar(Cliente cliente);
    void excluir (Long id);
    Cliente buscarPorCpf(Long cpf);
    List<Cliente> buscarTodos();
}
