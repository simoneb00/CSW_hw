package jdbc.dao;

import jdbc.exceptions.NonExistentPersonException;
import jdbc.model.Cane;
import jdbc.model.Persona;

import java.sql.*;

public class CaneDao {

    String dbURL = "jdbc:mysql://localhost:3306/csw_hw_jdbc_db";
    String username = "root";
    String password = "password";

    public void insert(Cane dog) throws NonExistentPersonException {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            String query = "INSERT INTO Cane (nome, razza, nome_padrone, cognome_padrone, nome_indirizzo_padrone) VALUES (?, ?, ?, ?, ?)";
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

    public void findDogsByOwner(Persona owner) {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            String query = "SELECT nome, razza FROM Cane WHERE nome_padrone = ? and cognome_padrone = ? and nome_indirizzo_padrone = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, owner.getNome());
            statement.setString(2, owner.getCognome());
            statement.setString(3, owner.getIndirizzo().getNome());
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\nRisultati:");
            while (resultSet.next()) {
                System.out.println("Nome      Razza");
                System.out.println(resultSet.getString(1) + "     " + resultSet.getString(2));
            }
            System.out.println("\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
