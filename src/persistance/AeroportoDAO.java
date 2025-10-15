package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Aeroporto;

public class AeroportoDAO {

    private static final String INSERT_SQL = "INSERT INTO Aeroportos (nome, localizacao, id_companhia) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nome, localizacao, id_companhia FROM Aeroportos WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, nome, localizacao, id_companhia FROM Aeroportos";
    private static final String UPDATE_SQL = "UPDATE Aeroportos SET nome = ?, localizacao = ?, id_companhia = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Aeroportos WHERE id = ?";
    private static final String SELECT_BY_COMPANHIA_ID_SQL = "SELECT id, nome, localizacao, id_companhia FROM Aeroportos WHERE id_companhia = ?";

    public Aeroporto create(Aeroporto aeroporto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, aeroporto.getNome());
            preparedStatement.setString(2, aeroporto.getLocalizacao());
            preparedStatement.setInt(3, aeroporto.getCompanhiaId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    aeroporto.setId(rs.getInt(1));
                }
            }
            return aeroporto;
        }
    }

    public Aeroporto findById(long id) throws SQLException {
        Aeroporto aeroporto = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    aeroporto = new Aeroporto();
                    aeroporto.setId(resultSet.getInt("id"));
                    aeroporto.setNome(resultSet.getString("nome"));
                    aeroporto.setLocalizacao(resultSet.getString("localizacao"));
                    aeroporto.setCompanhiaId(resultSet.getInt("id_companhia"));
                }
            }
        }
        return aeroporto;
    }

    public List<Aeroporto> findAll() throws SQLException {
        List<Aeroporto> aeroportos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Aeroporto aeroporto = new Aeroporto();
                aeroporto.setId(resultSet.getInt("id"));
                aeroporto.setNome(resultSet.getString("nome"));
                aeroporto.setLocalizacao(resultSet.getString("localizacao"));
                aeroporto.setCompanhiaId(resultSet.getInt("id_companhia"));
                aeroportos.add(aeroporto);
            }
        }
        return aeroportos;
    }

    public void update(Aeroporto aeroporto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, aeroporto.getNome());
            preparedStatement.setString(2, aeroporto.getLocalizacao());
            preparedStatement.setInt(3, aeroporto.getCompanhiaId());
            preparedStatement.setInt(4, aeroporto.getId());
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

    public List<Aeroporto> findAllByCompanhiaId(int companhiaId) throws SQLException {
        List<Aeroporto> aeroportos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_COMPANHIA_ID_SQL)) {
            preparedStatement.setInt(1, companhiaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Aeroporto aeroporto = new Aeroporto();
                    aeroporto.setId(resultSet.getInt("id"));
                    aeroporto.setNome(resultSet.getString("nome"));
                    aeroporto.setLocalizacao(resultSet.getString("localizacao"));
                    aeroporto.setCompanhiaId(resultSet.getInt("id_companhia"));
                    aeroportos.add(aeroporto);
                }
            }
        }
        return aeroportos;
    }

    public void deleteAll() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Aeroportos")) {
            preparedStatement.executeUpdate();
        }
    }
}