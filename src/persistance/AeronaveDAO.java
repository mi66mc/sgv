package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Aeronave;

public class AeronaveDAO {

    private static final String INSERT_SQL = "INSERT INTO Aeronave (nome, peso, qtd_assentos_total, id_companhia) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nome, peso, qtd_assentos_total, id_companhia FROM Aeronave WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, nome, peso, qtd_assentos_total, id_companhia FROM Aeronave";
    private static final String UPDATE_SQL = "UPDATE Aeronave SET nome = ?, peso = ?, qtd_assentos_total = ?, id_companhia = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Aeronave WHERE id = ?";
    private static final String SELECT_BY_COMPANHIA_ID_SQL = "SELECT id, nome, peso, qtd_assentos_total, id_companhia FROM Aeronave WHERE id_companhia = ?";

    public Aeronave create(Aeronave aeronave) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, aeronave.getNome());
            preparedStatement.setDouble(2, aeronave.getPeso());
            preparedStatement.setLong(3, aeronave.getQtdAssentosTotal());
            preparedStatement.setLong(4, aeronave.getCompanhiaId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    aeronave.setId(rs.getInt(1));
                }
            }
            return aeronave;
        }
    }

    public Aeronave findById(long id) throws SQLException {
        Aeronave aeronave = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    aeronave = new Aeronave();
                    aeronave.setId(resultSet.getInt("id"));
                    aeronave.setNome(resultSet.getString("nome"));
                    aeronave.setPeso(resultSet.getDouble("peso"));
                    aeronave.setQtdAssentosTotal(resultSet.getInt("qtd_assentos_total"));
                    aeronave.setCompanhiaId(resultSet.getInt("id_companhia"));
                }
            }
        }
        return aeronave;
    }

    public List<Aeronave> findAll() throws SQLException {
        List<Aeronave> aeronaves = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Aeronave aeronave = new Aeronave();
                aeronave.setId(resultSet.getInt("id"));
                aeronave.setNome(resultSet.getString("nome"));
                aeronave.setPeso(resultSet.getDouble("peso"));
                aeronave.setQtdAssentosTotal(resultSet.getInt("qtd_assentos_total"));
                aeronave.setCompanhiaId(resultSet.getInt("id_companhia"));
                aeronaves.add(aeronave);
            }
        }
        return aeronaves;
    }

    public void update(Aeronave aeronave) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, aeronave.getNome());
            preparedStatement.setDouble(2, aeronave.getPeso());
            preparedStatement.setLong(3, aeronave.getQtdAssentosTotal());
            preparedStatement.setLong(4, aeronave.getCompanhiaId());
            preparedStatement.setLong(5, aeronave.getId());
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

    public List<Aeronave> findAllByCompanhiaId(int companhiaId) throws SQLException {
        List<Aeronave> aeronaves = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_COMPANHIA_ID_SQL)) {
            preparedStatement.setLong(1, companhiaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Aeronave aeronave = new Aeronave();
                    aeronave.setId(resultSet.getInt("id"));
                    aeronave.setNome(resultSet.getString("nome"));
                    aeronave.setPeso(resultSet.getDouble("peso"));
                    aeronave.setQtdAssentosTotal(resultSet.getInt("qtd_assentos_total"));
                    aeronave.setCompanhiaId(resultSet.getInt("id_companhia"));
                    aeronaves.add(aeronave);
                }
            }
        }
        return aeronaves;
    }

    public void deleteAll() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Aeronave")) {
            preparedStatement.executeUpdate();
        }
    }
}