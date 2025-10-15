package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Gerente;

public class GerenteDAO {

    private static final String INSERT_SQL = "INSERT INTO Gerente (nome, senha, id_companhia) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nome, senha, id_companhia FROM Gerente WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, nome, senha, id_companhia FROM Gerente";
    private static final String UPDATE_SQL = "UPDATE Gerente SET nome = ?, senha = ?, id_companhia = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Gerente WHERE id = ?";

    public Gerente create(Gerente gerente) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, gerente.getNome());
            preparedStatement.setString(2, gerente.getSenha());
            preparedStatement.setInt(3, gerente.getCompanhiaId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    gerente.setId(rs.getInt(1));
                }
            }
            return gerente;
        }
    }

    public Gerente findById(long id) throws SQLException {
        Gerente gerente = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    gerente = new Gerente();
                    gerente.setId(resultSet.getInt("id"));
                    gerente.setNome(resultSet.getString("nome"));
                    gerente.setSenha(resultSet.getString("senha"));
                    gerente.setCompanhiaId(resultSet.getInt("id_companhia"));
                }
            }
        }
        return gerente;
    }

    public List<Gerente> findAll() throws SQLException {
        List<Gerente> gerentes = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Gerente gerente = new Gerente();
                gerente.setId(resultSet.getInt("id"));
                gerente.setNome(resultSet.getString("nome"));
                gerente.setSenha(resultSet.getString("senha"));
                gerente.setCompanhiaId(resultSet.getInt("id_companhia"));
                gerentes.add(gerente);
            }
        }
        return gerentes;
    }

    public void update(Gerente gerente) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, gerente.getNome());
            preparedStatement.setString(2, gerente.getSenha());
            preparedStatement.setInt(3, gerente.getCompanhiaId());
            preparedStatement.setInt(4, gerente.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Gerente")) {
            preparedStatement.executeUpdate();
        }
    }
}