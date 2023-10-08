package dao;

import exceptions.NonExistentAddressException;
import exceptions.NonExistentPersonException;
import model.Cane;
import model.Persona;

import java.sql.*;

public class CaneDao {

    String dbURL = "jdbc:mysql://localhost:3306/csw_hw_jdbc_db";
    String username = "root";
    String password = "password";

    public void insert(Cane dog) throws NonExistentPersonException {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            String query = "INSERT INTO Cane (nome, razza, nome_padrone, cognome_padrone, indirizzo_padrone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dog.getNome());
            statement.setString(2, dog.getRazza());
            statement.setString(3, dog.getPadrone().getNome());
            statement.setString(4, dog.getPadrone().getCognome());
            statement.setString(5, dog.getPadrone().getIndirizzo().getNome());
            statement.executeUpdate();

            System.out.println("Record correttamente inserito\n");

            connection.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NonExistentPersonException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
