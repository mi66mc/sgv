package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Voo implements Identifiable {
    private long id;
    private LocalDate data;
    private LocalTime hora;
    private FlightStatus status;
    private long qtdAssentos;

    private long pilotoId;
    private Piloto piloto;

    private long rotaId;
    private Rota rota;

    private long companhiaId;
    private CompanhiaAerea companhia;

    private long aeronaveId;
    private Aeronave aeronave;

    public Voo() {}

    public Voo(long id, LocalDate data, LocalTime hora, FlightStatus status, long qtdAssentos,
               long pilotoId, Piloto piloto, long rotaId, Rota rota,
               long companhiaId, CompanhiaAerea companhia,
               long aeronaveId, Aeronave aeronave) {
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
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public FlightStatus getStatus() { return status; }
    public void setStatus(FlightStatus status) { this.status = status; }

    public long getQtdAssentos() { return qtdAssentos; }
    public void setQtdAssentos(long qtdAssentos) { this.qtdAssentos = qtdAssentos; }

    public long getPilotoId() { return pilotoId; }
    public void setPilotoId(long pilotoId) { this.pilotoId = pilotoId; }

    public Piloto getPiloto() { return piloto; }
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
        this.pilotoId = piloto != null ? piloto.getId() : this.pilotoId;
    }

    public long getRotaId() { return rotaId; }
    public void setRotaId(long rotaId) { this.rotaId = rotaId; }

    public Rota getRota() { return rota; }
    public void setRota(Rota rota) {
        this.rota = rota;
        this.rotaId = rota != null ? rota.getId() : this.rotaId;
    }

    public long getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(long companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }

    public long getAeronaveId() { return aeronaveId; }
    public void setAeronaveId(long aeronaveId) { this.aeronaveId = aeronaveId; }

    public Aeronave getAeronave() { return aeronave; }
    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
        this.aeronaveId = aeronave != null ? aeronave.getId() : this.aeronaveId;
    }
}
