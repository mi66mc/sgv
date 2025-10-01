package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rota implements Identifiable {
    private Integer id;
    private String nome;

    private List<RotaAeroporto> paradas;

    public Rota() {
        this.paradas = new ArrayList<>();
    }

    public Rota(Integer id, String nome, List<RotaAeroporto> paradas) {
        this.id = id;
        this.nome = nome;
        this.paradas = paradas != null ? paradas : new ArrayList<>();
        for (RotaAeroporto ra : this.paradas) {
            if (ra.getRota() != this) ra.setRota(this);
        }
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<RotaAeroporto> getParadas() { return paradas; }
    public void setParadas(List<RotaAeroporto> paradas) {
        this.paradas = paradas != null ? paradas : new ArrayList<>();
        for (RotaAeroporto ra : this.paradas) {
            if (ra.getRota() != this) ra.setRota(this);
        }
    }

    public void addParada(RotaAeroporto parada) {
        if (parada == null) return;
        parada.setRota(this);
        if (!this.paradas.contains(parada)) this.paradas.add(parada);
    }

    public void removeParada(RotaAeroporto parada) {
        if (parada == null) return;
        if (this.paradas.remove(parada)) {
            parada.setRota(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rota)) return false;
        Rota rota = (Rota) o;
        if (this.id == null || rota.id == null) return false;
        return Objects.equals(id, rota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
