package dao;

import model.Indirizzo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IndirizzoDao {

    String dbURL = "jdbc:mysql://localhost:3306/csw_hw_jdbc_db";
    String username = "root";
    String password = "password";

    public void insert(Indirizzo address) {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query = "INSERT INTO Indirizzo (nome) VALUES ('" + address.getNome() + "')";
            connection.createStatement().executeUpdate(query);

            System.out.println("Record correttamente inserito\n");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
