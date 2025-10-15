package model;

import java.util.Objects;

public class RotaAeroporto implements Identifiable {
    private Integer id;

    private Integer rotaId;
    private Integer aeroportoId;

    private Rota rota;
    private Aeroporto aeroporto;

    private Integer posicao;

    public RotaAeroporto() {}

    public RotaAeroporto(Integer id, Integer rotaId, Integer aeroportoId, Rota rota, Aeroporto aeroporto, Integer posicao) {
        this.id = id;
        this.rotaId = rotaId;
        this.aeroportoId = aeroportoId;
        this.rota = rota;
        this.aeroporto = aeroporto;
        this.posicao = posicao;
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getRotaId() { return rotaId; }
    public void setRotaId(Integer rotaId) { this.rotaId = rotaId; }

    public Integer getAeroportoId() { return aeroportoId; }
    public void setAeroportoId(Integer aeroportoId) { this.aeroportoId = aeroportoId; }

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

    public Integer getPosicao() { return posicao; }
    public void setPosicao(Integer posicao) { this.posicao = posicao; }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RotaAeroporto{" +
                "id=" + id +
                ", rotaId=" + rotaId +
                ", aeroportoId=" + aeroportoId +
                ", posicao=" + posicao +
                '}';
    }
}
