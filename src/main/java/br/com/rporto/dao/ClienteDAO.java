package br.com.rporto.dao;

import br.com.rporto.domain.Cliente;
import br.com.rporto.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public Cliente buscarPorCpf(Long cpf) {
        String sql = "SELECT id, nome, cpf, tel, endereco, cidade * FROM TB_CLIENTE WHERE cpf = ?";
        Cliente cliente = null;
        try(Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stm = conexao.prepareStatement(sql)) {

            stm.setLong(1,cpf); //passando o cpf pra query

            ResultSet rs = stm.executeQuery();//o Resulset nada mais é do que uma tabela que armazena os resultados da consulta SQL

            if(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getLong("cpf"));
                cliente.setTel(rs.getLong("tel"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCidade(rs.getString("cidade"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void salvar(Cliente cliente) {
        //preparando a query pra database
        String sql = "INSERT INTO TB_CLIENTE (id, nome, cpf, tel, endereco, cidade) VALUES (?, ?, ?, ?, ?, ?)";
        //aqui no try, estou usando o try-with-resource. basicamente ele já fecha a conexao automaticamente pra mim porque ele implementa a interface
        //AutoCloseable, que surgiu a partir do Java 7. é melhor usar ele, pois fica menos verboso e menos sujeito a erro.
        //Assim que o bloco try termina, ele automaticamente executa conexao.close e stm.close
        try (Connection conexao = ConnectionFactory.getConnection();
             //o stm prepara a query pra ser lançada la no banco de dados
             PreparedStatement stm = conexao.prepareStatement(sql)){
            stm.setLong(1, cliente.getId());
            stm.setString(2, cliente.getNome());
            stm.setLong(3, cliente.getCpf());
            stm.setLong(4, cliente.getTel());
            stm.setString(5, cliente.getEndereco());
            stm.setString(6, cliente.getCidade());
            //executa a query SQL feita, no caso é um insert
            stm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE TB_CLIENTE SET nome = ?, cpf = ?, tel = ?, endereco = ?, cidade = ? WHERE id = ?";
        try(Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stm = conexao.prepareStatement(sql)){
            stm.setLong(1, cliente.getId());
            stm.setString(2, cliente.getNome());
            stm.setLong(3, cliente.getCpf());
            stm.setLong(4, cliente.getTel());
            stm.setString(5, cliente.getEndereco());
            stm.setString(6, cliente.getCidade());

          int verificacao =  stm.executeUpdate();
            if (verificacao > 0){
                System.out.println("Clinte "+ cliente.getId() + " atualizado!");
            }else {
                System.out.println("Nenhum cliente encontrado com o ID: " + cliente.getId());
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(Long id) {
    String sql = "DELETE FROM TB_CLIENTE WHERE id = ?";

    try (Connection conexao = ConnectionFactory.getConnection();
    PreparedStatement stm = conexao.prepareStatement(sql)){
        stm.setLong(1, id); //substitui o placeholder ? pelo id

        int verificacao = stm.executeUpdate();

        if(verificacao > 0){
            System.out.println("Cliente com ID "+ id + " excluído com sucesso!");
        }else {
            System.out.println("Nenhum cliente com ID " + id + " encontrado!");
        }
    }catch (SQLException e){
        e.printStackTrace();
    }

    }



    @Override
    public List<Cliente> buscarTodos() {
        String sql = "SELECT id, nome, cpf, tel, endereco, cidade FROM TB_CLIENTE";
        List<Cliente> listaCliente = new ArrayList<>();
        try(Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stm = conexao.prepareStatement(sql)){

            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getLong("cpf"));
                cliente.setTel(rs.getLong("tel"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCidade(rs.getString("cidade"));

                listaCliente.add(cliente);
            }
        }catch (SQLException e){
            System.out.println("Erro ao buscar todos os clientes: " + e.getMessage());
        }
        return listaCliente;

    }
}
