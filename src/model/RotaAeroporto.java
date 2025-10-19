package model;

public class RotaAeroporto implements Identifiable {
    private long id;

    private long rotaId;
    private long aeroportoId;

    private Rota rota;
    private Aeroporto aeroporto;

    private long posicao;

    public RotaAeroporto() {}

    public RotaAeroporto(long id, long rotaId, long aeroportoId, Rota rota, Aeroporto aeroporto, long posicao) {
        this.id = id;
        this.rotaId = rotaId;
        this.aeroportoId = aeroportoId;
        this.rota = rota;
        this.aeroporto = aeroporto;
        this.posicao = posicao;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getRotaId() { return rotaId; }
    public void setRotaId(long rotaId) { this.rotaId = rotaId; }

    public long getAeroportoId() { return aeroportoId; }
    public void setAeroportoId(long aeroportoId) { this.aeroportoId = aeroportoId; }

    public Rota getRota() { return rota; }
    public void setRota(Rota rota) {
        if (this.rota != rota) {
            if (this.rota != null && this.rota.getParadas() != null) {
                this.rota.getParadas().remove(this);
            }
            this.rota = rota;
            this.rotaId = rota != null ? rota.getId() : this.rotaId;
            if (rota != null && rota.getParadas() != null && !rota.getParadas().contains(this)) {
                rota.getParadas().add(this);
            }
        }
    }

    public Aeroporto getAeroporto() { return aeroporto; }
    public void setAeroporto(Aeroporto aeroporto) {
        this.aeroporto = aeroporto;
        this.aeroportoId = aeroporto != null ? aeroporto.getId() : this.aeroportoId;
    }

    public long getPosicao() { return posicao; }
    public void setPosicao(long posicao) { this.posicao = posicao; }
}
