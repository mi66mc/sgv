package model;

import java.time.LocalDateTime;

public class Reserva implements Identifiable {
    private long id;
    private LocalDateTime reservadoEm;
    private Boolean cancelado;

    private long vooId;
    private Voo voo;

    private long passageiroId;
    private Passageiro passageiro;

    public Reserva() {}

    public Reserva(long id, LocalDateTime reservadoEm, Boolean cancelado,
                   long vooId, Voo voo, long passageiroId, Passageiro passageiro) {
        this.id = id;
        this.reservadoEm = reservadoEm;
        this.cancelado = cancelado;
        this.vooId = vooId;
        this.voo = voo;
        this.passageiroId = passageiroId;
        this.passageiro = passageiro;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LocalDateTime getReservadoEm() { return reservadoEm; }
    public void setReservadoEm(LocalDateTime reservadoEm) { this.reservadoEm = reservadoEm; }

    public Boolean getCancelado() { return cancelado; }
    public void setCancelado(Boolean cancelado) { this.cancelado = cancelado; }

    public long getVooId() { return vooId; }
    public void setVooId(long vooId) { this.vooId = vooId; }

    public Voo getVoo() { return voo; }
    public void setVoo(Voo voo) {
        this.voo = voo;
        this.vooId = voo != null ? voo.getId() : this.vooId;
    }

    public long getPassageiroId() { return passageiroId; }
    public void setPassageiroId(long passageiroId) { this.passageiroId = passageiroId; }

    public Passageiro getPassageiro() { return passageiro; }
    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
        this.passageiroId = passageiro != null ? passageiro.getId() : this.passageiroId;
    }
}
