package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reserva implements Identifiable {
    private Integer id;
    private LocalDateTime reservadoEm;
    private Boolean cancelado;

    private Integer vooId;
    private Voo voo;

    private Integer passageiroId;
    private Passageiro passageiro;

    public Reserva() {}

    public Reserva(Integer id, LocalDateTime reservadoEm, Boolean cancelado,
                   Integer vooId, Voo voo, Integer passageiroId, Passageiro passageiro) {
        this.id = id;
        this.reservadoEm = reservadoEm;
        this.cancelado = cancelado;
        this.vooId = vooId;
        this.voo = voo;
        this.passageiroId = passageiroId;
        this.passageiro = passageiro;
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getReservadoEm() { return reservadoEm; }
    public void setReservadoEm(LocalDateTime reservadoEm) { this.reservadoEm = reservadoEm; }

    public Boolean getCancelado() { return cancelado; }
    public void setCancelado(Boolean cancelado) { this.cancelado = cancelado; }

    public Integer getVooId() { return vooId; }
    public void setVooId(Integer vooId) { this.vooId = vooId; }

    public Voo getVoo() { return voo; }
    public void setVoo(Voo voo) {
        this.voo = voo;
        this.vooId = voo != null ? voo.getId() : this.vooId;
    }

    public Integer getPassageiroId() { return passageiroId; }
    public void setPassageiroId(Integer passageiroId) { this.passageiroId = passageiroId; }

    public Passageiro getPassageiro() { return passageiro; }
    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
        this.passageiroId = passageiro != null ? passageiro.getId() : this.passageiroId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
