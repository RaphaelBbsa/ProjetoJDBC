package br.com.rporto.dao;

import br.com.rporto.domain.Produto;
import br.com.rporto.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO{

    @Override
    public void salvar(Produto produto) {
        String sql = "INSERT INTO TB_PRODUTO (nome, descricao, preco) VALUES (?, ?, ?)";
        try(Connection conexao = ConnectionFactory.getConnection();
            //passei o GENERATED KEYS como parâmetro, pois irei incrementar o id automatico do produto na database. Assim, esse método
            //me retorna o id gerado automaticamente. É um conceito novo que aprendi ainda agora
            PreparedStatement stm = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getDescricao());
            stm.setDouble(3,produto.getPreco());
            stm.executeUpdate();

            try(ResultSet chaveGerada = stm.getGeneratedKeys()){
                if(chaveGerada.next()){
                    produto.setId(chaveGerada.getLong(1));//Aqui eu coloco 1 como parâmetro, pois o id gerado irá ficar na primeira coluna
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao salvar o produto: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Produto produto) {
        String sql = "UPDATE TB_PRODUTO SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
        try(Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stm = conexao.prepareStatement(sql)){
            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getDescricao());
            stm.setDouble(3, produto.getPreco());
            stm.setLong(4, produto.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Long id) {
        String sql = "DELETE FROM TB_PRODUTO WHERE id = ?";
        try(Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stm = conexao.prepareStatement(sql)){
            stm.setLong(1,id);
            stm.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao excluir esse produto: "+ e.getMessage());
        }

    }

    @Override
    public Produto buscarPorId(Long id) {
        String sql = "SELECT id, nome, descricao, preco FROM TB_PRODUTO where id = ?";
        Produto produto = null;

        try (Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stm = conexao.prepareStatement(sql)){
        stm.setLong(1, id);
        try(ResultSet rs = stm.executeQuery()){
            if(rs.next()){
                produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
            }
        }
        }catch (SQLException e){
            System.out.println("Erro ao buscar produto: "+ e.getMessage());
        }
        return produto;
    }

    @Override
    public List<Produto> buscarTodos() {
        return List.of();
    }
}
