package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.RotaAeroporto;

public class RotaAeroportoDAO {

    private static final String INSERT_SQL = "INSERT INTO Rotas_Aeroportos (id_rota, id_aeroporto, posicao) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, id_rota, id_aeroporto, posicao FROM Rotas_Aeroportos WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, id_rota, id_aeroporto, posicao FROM Rotas_Aeroportos";
    private static final String UPDATE_SQL = "UPDATE Rotas_Aeroportos SET id_rota = ?, id_aeroporto = ?, posicao = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Rotas_Aeroportos WHERE id = ?";

    public RotaAeroporto create(RotaAeroporto rotaAeroporto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, rotaAeroporto.getRotaId());
            preparedStatement.setLong(2, rotaAeroporto.getAeroportoId());
            preparedStatement.setLong(3, rotaAeroporto.getPosicao());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    rotaAeroporto.setId(rs.getInt(1));
                }
            }
            return rotaAeroporto;
        }
    }

    public RotaAeroporto findById(long id) throws SQLException {
        RotaAeroporto rotaAeroporto = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    rotaAeroporto = new RotaAeroporto();
                    rotaAeroporto.setId(resultSet.getInt("id"));
                    rotaAeroporto.setRotaId(resultSet.getInt("id_rota"));
                    rotaAeroporto.setAeroportoId(resultSet.getInt("id_aeroporto"));
                    rotaAeroporto.setPosicao(resultSet.getInt("posicao"));
                }
            }
        }
        return rotaAeroporto;
    }

    public List<RotaAeroporto> findAll() throws SQLException {
        List<RotaAeroporto> rotasAeroportos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RotaAeroporto rotaAeroporto = new RotaAeroporto();
                rotaAeroporto.setId(resultSet.getInt("id"));
                rotaAeroporto.setRotaId(resultSet.getInt("id_rota"));
                rotaAeroporto.setAeroportoId(resultSet.getInt("id_aeroporto"));
                rotaAeroporto.setPosicao(resultSet.getInt("posicao"));
                rotasAeroportos.add(rotaAeroporto);
            }
        }
        return rotasAeroportos;
    }

    public void update(RotaAeroporto rotaAeroporto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, rotaAeroporto.getRotaId());
            preparedStatement.setLong(2, rotaAeroporto.getAeroportoId());
            preparedStatement.setLong(3, rotaAeroporto.getPosicao());
            preparedStatement.setLong(4, rotaAeroporto.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Rotas_Aeroportos")) {
            preparedStatement.executeUpdate();
        }
    }
}