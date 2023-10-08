package dao;

import exceptions.NonExistentAddressException;
import model.Indirizzo;
import model.Persona;

import java.sql.*;

public class PersonaDao {

    String dbURL = "jdbc:mysql://localhost:3306/csw_hw_jdbc_db";
    String username = "root";
    String password = "password";

    public void insert(Persona person) throws NonExistentAddressException {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            String query = "INSERT INTO Persona (nome, cognome, indirizzo) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, person.getNome());
            statement.setString(2, person.getCognome());
            statement.setString(3, person.getIndirizzo().getNome());
            statement.executeUpdate();

            System.out.println("Record correttamente inserito\n");

            connection.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NonExistentAddressException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
