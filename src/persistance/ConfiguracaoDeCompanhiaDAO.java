package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ConfiguracaoDeCompanhia;

public class ConfiguracaoDeCompanhiaDAO {

    private static final String INSERT_SQL = "INSERT INTO Configuracao_de_companhia (passageiro_cancelar, id_companhia) VALUES (?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, passageiro_cancelar, id_companhia FROM Configuracao_de_companhia WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, passageiro_cancelar, id_companhia FROM Configuracao_de_companhia";
    private static final String UPDATE_SQL = "UPDATE Configuracao_de_companhia SET passageiro_cancelar = ?, id_companhia = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Configuracao_de_companhia WHERE id = ?";

    public ConfiguracaoDeCompanhia create(ConfiguracaoDeCompanhia configuracao) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, configuracao.getPassageiroCancelar());
            preparedStatement.setLong(2, configuracao.getCompanhiaId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    configuracao.setId(rs.getInt(1));
                }
            }
            return configuracao;
        }
    }

    public ConfiguracaoDeCompanhia findById(long id) throws SQLException {
        ConfiguracaoDeCompanhia configuracao = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    configuracao = new ConfiguracaoDeCompanhia();
                    configuracao.setId(resultSet.getInt("id"));
                    configuracao.setPassageiroCancelar(resultSet.getBoolean("passageiro_cancelar"));
                    configuracao.setCompanhiaId(resultSet.getInt("id_companhia"));
                }
            }
        }
        return configuracao;
    }

    public List<ConfiguracaoDeCompanhia> findAll() throws SQLException {
        List<ConfiguracaoDeCompanhia> configuracoes = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ConfiguracaoDeCompanhia configuracao = new ConfiguracaoDeCompanhia();
                configuracao.setId(resultSet.getInt("id"));
                configuracao.setPassageiroCancelar(resultSet.getBoolean("passageiro_cancelar"));
                configuracao.setCompanhiaId(resultSet.getInt("id_companhia"));
                configuracoes.add(configuracao);
            }
        }
        return configuracoes;
    }

    public void update(ConfiguracaoDeCompanhia configuracao) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setBoolean(1, configuracao.getPassageiroCancelar());
            preparedStatement.setLong(2, configuracao.getCompanhiaId());
            preparedStatement.setLong(3, configuracao.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Configuracao_de_companhia")) {
            preparedStatement.executeUpdate();
        }
    }
}