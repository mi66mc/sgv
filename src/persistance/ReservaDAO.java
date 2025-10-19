package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;

public class ReservaDAO {

    private static final String INSERT_SQL = "INSERT INTO Reserva (reservado_em, cancelado, id_voo, id_passageiro) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, reservado_em, cancelado, id_voo, id_passageiro FROM Reserva WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, reservado_em, cancelado, id_voo, id_passageiro FROM Reserva";
    private static final String UPDATE_SQL = "UPDATE Reserva SET reservado_em = ?, cancelado = ?, id_voo = ?, id_passageiro = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Reserva WHERE id = ?";

    public Reserva create(Reserva reserva) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(reserva.getReservadoEm()));
            preparedStatement.setBoolean(2, reserva.getCancelado());
            preparedStatement.setLong(3, reserva.getVooId());
            preparedStatement.setLong(4, reserva.getPassageiroId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    reserva.setId(rs.getInt(1));
                }
            }
            return reserva;
        }
    }

    public Reserva findById(long id) throws SQLException {
        Reserva reserva = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = new Reserva();
                    reserva.setId(resultSet.getInt("id"));
                    reserva.setReservadoEm(resultSet.getTimestamp("reservado_em").toLocalDateTime());
                    reserva.setCancelado(resultSet.getBoolean("cancelado"));
                    reserva.setVooId(resultSet.getInt("id_voo"));
                    reserva.setPassageiroId(resultSet.getInt("id_passageiro"));
                }
            }
        }
        return reserva;
    }

    public List<Reserva> findAll() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(resultSet.getInt("id"));
                reserva.setReservadoEm(resultSet.getTimestamp("reservado_em").toLocalDateTime());
                reserva.setCancelado(resultSet.getBoolean("cancelado"));
                reserva.setVooId(resultSet.getInt("id_voo"));
                reserva.setPassageiroId(resultSet.getInt("id_passageiro"));
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public void update(Reserva reserva) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(reserva.getReservadoEm()));
            preparedStatement.setBoolean(2, reserva.getCancelado());
            preparedStatement.setLong(3, reserva.getVooId());
            preparedStatement.setLong(4, reserva.getPassageiroId());
            preparedStatement.setLong(5, reserva.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Reserva")) {
            preparedStatement.executeUpdate();
        }
    }
}