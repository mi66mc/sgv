package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.FlightStatus;
import model.Voo;

public class VooDAO {

    private static final String INSERT_SQL = "INSERT INTO Voos (data, hora, status, qtd_assentos, id_piloto, id_rota, id_companhia, id_aeronave) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, data, hora, status, qtd_assentos, id_piloto, id_rota, id_companhia, id_aeronave FROM Voos WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, data, hora, status, qtd_assentos, id_piloto, id_rota, id_companhia, id_aeronave FROM Voos";
    private static final String SELECT_ALL_BY_DATE_SQL = "SELECT id, data, hora, status, qtd_assentos, id_piloto, id_rota, id_companhia, id_aeronave FROM Voos WHERE data >= ?";
    private static final String UPDATE_SQL = "UPDATE Voos SET data = ?, hora = ?, status = ?, qtd_assentos = ?, id_piloto = ?, id_rota = ?, id_companhia = ?, id_aeronave = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Voos WHERE id = ?";

    public Voo create(Voo voo) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, Date.valueOf(voo.getData()));
            preparedStatement.setTime(2, Time.valueOf(voo.getHora()));
            preparedStatement.setString(3, voo.getStatus().toDbValue());
            preparedStatement.setLong(4, voo.getQtdAssentos());
            preparedStatement.setLong(5, voo.getPilotoId());
            preparedStatement.setLong(6, voo.getRotaId());
            preparedStatement.setLong(7, voo.getCompanhiaId());
            preparedStatement.setLong(8, voo.getAeronaveId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    voo.setId(rs.getInt(1));
                }
            }
            return voo;
        }
    }

    public Voo findById(long id) throws SQLException {
        Voo voo = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    voo = new Voo();
                    voo.setId(resultSet.getInt("id"));
                    voo.setData(resultSet.getDate("data").toLocalDate());
                    voo.setHora(resultSet.getTime("hora").toLocalTime());
                    voo.setStatus(FlightStatus.fromString(resultSet.getString("status")));
                    voo.setQtdAssentos(resultSet.getInt("qtd_assentos"));
                    voo.setPilotoId(resultSet.getInt("id_piloto"));
                    voo.setRotaId(resultSet.getInt("id_rota"));
                    voo.setCompanhiaId(resultSet.getInt("id_companhia"));
                    voo.setAeronaveId(resultSet.getInt("id_aeronave"));
                }
            }
        }
        return voo;
    }

    public List<Voo> findAll() throws SQLException {
        List<Voo> voos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Voo voo = new Voo();
                voo.setId(resultSet.getInt("id"));
                voo.setData(resultSet.getDate("data").toLocalDate());
                voo.setHora(resultSet.getTime("hora").toLocalTime());
                voo.setStatus(FlightStatus.fromString(resultSet.getString("status")));
                voo.setQtdAssentos(resultSet.getInt("qtd_assentos"));
                voo.setPilotoId(resultSet.getInt("id_piloto"));
                voo.setRotaId(resultSet.getInt("id_rota"));
                voo.setCompanhiaId(resultSet.getInt("id_companhia"));
                voo.setAeronaveId(resultSet.getInt("id_aeronave"));
                voos.add(voo);
            }
        }
        return voos;
    }

    public List<Voo> findAllByDate(LocalDate date) throws SQLException {
        List<Voo> voos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_DATE_SQL)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Voo voo = new Voo();
                    voo.setId(resultSet.getInt("id"));
                    voo.setData(resultSet.getDate("data").toLocalDate());
                    voo.setHora(resultSet.getTime("hora").toLocalTime());
                    voo.setStatus(FlightStatus.fromString(resultSet.getString("status")));
                    voo.setQtdAssentos(resultSet.getInt("qtd_assentos"));
                    voo.setPilotoId(resultSet.getInt("id_piloto"));
                    voo.setRotaId(resultSet.getInt("id_rota"));
                    voo.setCompanhiaId(resultSet.getInt("id_companhia"));
                    voo.setAeronaveId(resultSet.getInt("id_aeronave"));
                    voos.add(voo);
                }
            }
        }
        return voos;
    }

    public void update(Voo voo) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setDate(1, Date.valueOf(voo.getData()));
            preparedStatement.setTime(2, Time.valueOf(voo.getHora()));
            preparedStatement.setString(3, voo.getStatus().toDbValue());
            preparedStatement.setLong(4, voo.getQtdAssentos());
            preparedStatement.setLong(5, voo.getPilotoId());
            preparedStatement.setLong(6, voo.getRotaId());
            preparedStatement.setLong(7, voo.getCompanhiaId());
            preparedStatement.setLong(8, voo.getAeronaveId());
            preparedStatement.setLong(9, voo.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Voos")) {
            preparedStatement.executeUpdate();
        }
    }
}