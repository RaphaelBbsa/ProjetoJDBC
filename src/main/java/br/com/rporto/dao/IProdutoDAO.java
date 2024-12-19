package br.com.rporto.dao;

import br.com.rporto.domain.Produto;

import java.util.List;

public interface IProdutoDAO {

    void salvar (Produto produto);
    void atualizar (Produto produto);
    void excluir (Long id);
    Produto buscarPorId(Long id);
    List<Produto> buscarTodos();
}
