package jdbc.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabase {

    private CreateDatabase(){}
    public static void create() {


        String serverURL = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "password";
        String dbName = "csw_hw_jdbc_db";

        try {
            Connection connection = DriverManager.getConnection(serverURL, username, password);
            String createDBQuery = "CREATE DATABASE IF NOT EXISTS csw_hw_jdbc_db";
            connection.createStatement().executeUpdate(createDBQuery);

            connection.close();
            connection = DriverManager.getConnection(serverURL + dbName, username, password);

            String createTableIndirizzo =
                    "CREATE TABLE IF NOT EXISTS Indirizzo (" +
                            "nome VARCHAR(255)," +
                            "città VARCHAR(255)," +
                            "PRIMARY KEY (nome, città)" +
                            ")";

            String createTablePersona =
                    "CREATE TABLE IF NOT EXISTS Persona (" +
                    "nome VARCHAR(255)," +
                    "cognome VARCHAR(255)," +
                    "nome_indirizzo VARCHAR(255)," +
                    "città_indirizzo VARCHAR(255)," +
                    "PRIMARY KEY (nome, cognome, nome_indirizzo)," +
                    "FOREIGN KEY (nome_indirizzo, città_indirizzo) REFERENCES Indirizzo(nome, città)" +
                    ")";


            String createTableCane = "CREATE TABLE IF NOT EXISTS Cane (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255)," +
                    "razza VARCHAR(255)," +
                    "nome_padrone VARCHAR(255)," +
                    "cognome_padrone VARCHAR(255)," +
                    "nome_indirizzo_padrone VARCHAR(255)," +
                    "FOREIGN KEY (nome_padrone, cognome_padrone, nome_indirizzo_padrone) REFERENCES Persona(nome, cognome, nome_indirizzo)" +
                    ")";

            connection.createStatement().executeUpdate(createTableIndirizzo);
            connection.createStatement().executeUpdate(createTablePersona);
            connection.createStatement().executeUpdate(createTableCane);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
