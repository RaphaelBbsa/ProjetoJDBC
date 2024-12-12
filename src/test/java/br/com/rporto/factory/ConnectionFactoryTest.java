package br.com.rporto.factory;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class ConnectionFactoryTest {
    @Test
    public void connectionTest(){
        try (Connection connection = ConnectionFactory.getConnection()){
            assertNotNull("A conexão não deve ser nula", connection);
            System.out.println("Conexão estabelecida com sucesso!");

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados");
        }
    }
}
