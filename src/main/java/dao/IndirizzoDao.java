package dao;

import exceptions.DuplicateEntryException;
import model.Indirizzo;

import javax.naming.Binding;
import java.sql.*;

public class IndirizzoDao {

    String dbURL = "jdbc:mysql://localhost:3306/csw_hw_jdbc_db";
    String username = "root";
    String password = "password";

    public void insert(Indirizzo address) throws DuplicateEntryException {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String query = "INSERT INTO Indirizzo (nome, città) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, address.getNome());
            statement.setString(2, address.getCittà());
            statement.executeUpdate();

            System.out.println("Record correttamente inserito\n");

            connection.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicateEntryException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
