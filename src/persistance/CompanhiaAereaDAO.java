package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CompanhiaAerea;

public class CompanhiaAereaDAO {

    private static final String INSERT_SQL = "INSERT INTO Companhia_aerea (nome, nacionalidade, email, senha) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nome, nacionalidade, email, senha FROM Companhia_aerea WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, nome, nacionalidade, email, senha FROM Companhia_aerea";
    private static final String UPDATE_SQL = "UPDATE Companhia_aerea SET nome = ?, nacionalidade = ?, email = ?, senha = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Companhia_aerea WHERE id = ?";

    public CompanhiaAerea create(CompanhiaAerea companhiaAerea) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, companhiaAerea.getNome());
            preparedStatement.setString(2, companhiaAerea.getNacionalidade());
            preparedStatement.setString(3, companhiaAerea.getEmail());
            preparedStatement.setString(4, companhiaAerea.getSenha());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    companhiaAerea.setId(rs.getInt(1));
                }
            }
            return companhiaAerea;
        }
    }

    public CompanhiaAerea findById(long id) throws SQLException {
        CompanhiaAerea companhiaAerea = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    companhiaAerea = new CompanhiaAerea();
                    companhiaAerea.setId(resultSet.getInt("id"));
                    companhiaAerea.setNome(resultSet.getString("nome"));
                    companhiaAerea.setNacionalidade(resultSet.getString("nacionalidade"));
                    companhiaAerea.setEmail(resultSet.getString("email"));
                    companhiaAerea.setSenha(resultSet.getString("senha"));
                }
            }
        }
        return companhiaAerea;
    }

    public List<CompanhiaAerea> findAll() throws SQLException {
        List<CompanhiaAerea> companhiasAereas = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CompanhiaAerea companhiaAerea = new CompanhiaAerea();
                companhiaAerea.setId(resultSet.getInt("id"));
                companhiaAerea.setNome(resultSet.getString("nome"));
                companhiaAerea.setNacionalidade(resultSet.getString("nacionalidade"));
                companhiaAerea.setEmail(resultSet.getString("email"));
                companhiaAerea.setSenha(resultSet.getString("senha"));
                companhiasAereas.add(companhiaAerea);
            }
        }
        return companhiasAereas;
    }

    public void update(CompanhiaAerea companhiaAerea) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, companhiaAerea.getNome());
            preparedStatement.setString(2, companhiaAerea.getNacionalidade());
            preparedStatement.setString(3, companhiaAerea.getEmail());
            preparedStatement.setString(4, companhiaAerea.getSenha());
            preparedStatement.setInt(5, companhiaAerea.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteAll() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Companhia_aerea")) {
            preparedStatement.executeUpdate();
        }
    }
}