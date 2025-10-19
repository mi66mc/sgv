package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Rota;

public class RotaDAO {

    private static final String INSERT_SQL = "INSERT INTO Rotas (nome) VALUES (?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nome FROM Rotas WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, nome FROM Rotas";
    private static final String UPDATE_SQL = "UPDATE Rotas SET nome = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Rotas WHERE id = ?";

    public Rota create(Rota rota) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, rota.getNome());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    rota.setId(rs.getInt(1));
                }
            }
            return rota;
        }
    }

    public Rota findById(long id) throws SQLException {
        Rota rota = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    rota = new Rota();
                    rota.setId(resultSet.getInt("id"));
                    rota.setNome(resultSet.getString("nome"));
                }
            }
        }
        return rota;
    }

    public List<Rota> findAll() throws SQLException {
        List<Rota> rotas = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Rota rota = new Rota();
                rota.setId(resultSet.getInt("id"));
                rota.setNome(resultSet.getString("nome"));
                rotas.add(rota);
            }
        }
        return rotas;
    }

    public void update(Rota rota) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, rota.getNome());
            preparedStatement.setLong(2, rota.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Rotas")) {
            preparedStatement.executeUpdate();
        }
    }
}