package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Passageiro;

public class PassageiroDAO {

    private static final String INSERT_SQL = "INSERT INTO Passageiro (nome, cpf, email, senha) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nome, cpf, email, senha FROM Passageiro WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, nome, cpf, email, senha FROM Passageiro";
    private static final String UPDATE_SQL = "UPDATE Passageiro SET nome = ?, cpf = ?, email = ?, senha = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Passageiro WHERE id = ?";

    public Passageiro create(Passageiro passageiro) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passageiro.getNome());
            preparedStatement.setString(2, passageiro.getCpf());
            preparedStatement.setString(3, passageiro.getEmail());
            preparedStatement.setString(4, passageiro.getSenha());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    passageiro.setId(rs.getInt(1));
                }
            }
            return passageiro;
        }
    }

    public Passageiro findById(long id) throws SQLException {
        Passageiro passageiro = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    passageiro = new Passageiro();
                    passageiro.setId(resultSet.getInt("id"));
                    passageiro.setNome(resultSet.getString("nome"));
                    passageiro.setCpf(resultSet.getString("cpf"));
                    passageiro.setEmail(resultSet.getString("email"));
                    passageiro.setSenha(resultSet.getString("senha"));
                }
            }
        }
        return passageiro;
    }

    public List<Passageiro> findAll() throws SQLException {
        List<Passageiro> passageiros = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setId(resultSet.getInt("id"));
                passageiro.setNome(resultSet.getString("nome"));
                passageiro.setCpf(resultSet.getString("cpf"));
                passageiro.setEmail(resultSet.getString("email"));
                passageiro.setSenha(resultSet.getString("senha"));
                passageiros.add(passageiro);
            }
        }
        return passageiros;
    }

    public void update(Passageiro passageiro) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, passageiro.getNome());
            preparedStatement.setString(2, passageiro.getCpf());
            preparedStatement.setString(3, passageiro.getEmail());
            preparedStatement.setString(4, passageiro.getSenha());
            preparedStatement.setInt(5, passageiro.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Passageiro")) {
            preparedStatement.executeUpdate();
        }
    }
}