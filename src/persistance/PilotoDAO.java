package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Piloto;

public class PilotoDAO {

    private static final String INSERT_SQL = "INSERT INTO Piloto (nome, cpf, email, senha) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nome, cpf, email, senha FROM Piloto WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, nome, cpf, email, senha FROM Piloto";
    private static final String UPDATE_SQL = "UPDATE Piloto SET nome = ?, cpf = ?, email = ?, senha = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Piloto WHERE id = ?";

    public Piloto create(Piloto piloto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, piloto.getNome());
            preparedStatement.setString(2, piloto.getCpf());
            preparedStatement.setString(3, piloto.getEmail());
            preparedStatement.setString(4, piloto.getSenha());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    piloto.setId(rs.getInt(1));
                }
            }
            return piloto;
        }
    }

    public Piloto findById(long id) throws SQLException {
        Piloto piloto = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    piloto = new Piloto();
                    piloto.setId(resultSet.getInt("id"));
                    piloto.setNome(resultSet.getString("nome"));
                    piloto.setCpf(resultSet.getString("cpf"));
                    piloto.setEmail(resultSet.getString("email"));
                    piloto.setSenha(resultSet.getString("senha"));
                }
            }
        }
        return piloto;
    }

    public List<Piloto> findAll() throws SQLException {
        List<Piloto> pilotos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Piloto piloto = new Piloto();
                piloto.setId(resultSet.getInt("id"));
                piloto.setNome(resultSet.getString("nome"));
                piloto.setCpf(resultSet.getString("cpf"));
                piloto.setEmail(resultSet.getString("email"));
                piloto.setSenha(resultSet.getString("senha"));
                pilotos.add(piloto);
            }
        }
        return pilotos;
    }

    public void update(Piloto piloto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, piloto.getNome());
            preparedStatement.setString(2, piloto.getCpf());
            preparedStatement.setString(3, piloto.getEmail());
            preparedStatement.setString(4, piloto.getSenha());
            preparedStatement.setLong(5, piloto.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Piloto")) {
            preparedStatement.executeUpdate();
        }
    }
}