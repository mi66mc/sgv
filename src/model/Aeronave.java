package model;

import java.util.Objects;

public class Aeronave implements Identifiable {
    private Integer id;
    private String nome;
    private Double peso;
    private Integer qtdAssentosTotal;

    private Integer companhiaId;
    private CompanhiaAerea companhia;

    public Aeronave() {}

    public Aeronave(Integer id, String nome, Double peso, Integer qtdAssentosTotal, Integer companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.qtdAssentosTotal = qtdAssentosTotal;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Integer getQtdAssentosTotal() { return qtdAssentosTotal; }
    public void setQtdAssentosTotal(Integer qtdAssentosTotal) { this.qtdAssentosTotal = qtdAssentosTotal; }

    public Integer getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(Integer companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aeronave)) return false;
        Aeronave aeronave = (Aeronave) o;
        if (this.id == null || aeronave.id == null) return false;
        return Objects.equals(id, aeronave.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
