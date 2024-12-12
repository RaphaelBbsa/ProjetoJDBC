package br.com.rporto.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
a
public class ConnectionFactory {
    private static String url;
    private static String username;
    private static String password;

static{

        try {
            //criando isso para carregar as propriedades do banco que eu configurei em resource
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/config.properties"));
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

            Class.forName(props.getProperty("db.driver"));

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar configurações do banco de dados", e);
        }

    }

    public static Connection getConnection () throws SQLException {
    return DriverManager.getConnection(url,username, password);
    }
}

