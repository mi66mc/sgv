package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Voo implements Identifiable {
    private Integer id;
    private LocalDate data;
    private LocalTime hora;
    private FlightStatus status;
    private Integer qtdAssentos;

    private Integer pilotoId;
    private Piloto piloto;

    private Integer rotaId;
    private Rota rota;

    private Integer companhiaId;
    private CompanhiaAerea companhia;

    private Integer aeronaveId;
    private Aeronave aeronave;

    public Voo() {}

    public Voo(Integer id, LocalDate data, LocalTime hora, FlightStatus status, Integer qtdAssentos,
               Integer pilotoId, Piloto piloto, Integer rotaId, Rota rota,
               Integer companhiaId, CompanhiaAerea companhia,
               Integer aeronaveId, Aeronave aeronave) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.status = status;
        this.qtdAssentos = qtdAssentos;
        this.pilotoId = pilotoId;
        this.piloto = piloto;
        this.rotaId = rotaId;
        this.rota = rota;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
        this.aeronaveId = aeronaveId;
        this.aeronave = aeronave;
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public FlightStatus getStatus() { return status; }
    public void setStatus(FlightStatus status) { this.status = status; }

    public Integer getQtdAssentos() { return qtdAssentos; }
    public void setQtdAssentos(Integer qtdAssentos) { this.qtdAssentos = qtdAssentos; }

    public Integer getPilotoId() { return pilotoId; }
    public void setPilotoId(Integer pilotoId) { this.pilotoId = pilotoId; }

    public Piloto getPiloto() { return piloto; }
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
        this.pilotoId = piloto != null ? piloto.getId() : this.pilotoId;
    }

    public Integer getRotaId() { return rotaId; }
    public void setRotaId(Integer rotaId) { this.rotaId = rotaId; }

    public Rota getRota() { return rota; }
    public void setRota(Rota rota) {
        this.rota = rota;
        this.rotaId = rota != null ? rota.getId() : this.rotaId;
    }

    public Integer getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(Integer companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }

    public Integer getAeronaveId() { return aeronaveId; }
    public void setAeronaveId(Integer aeronaveId) { this.aeronaveId = aeronaveId; }

    public Aeronave getAeronave() { return aeronave; }
    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
        this.aeronaveId = aeronave != null ? aeronave.getId() : this.aeronaveId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
