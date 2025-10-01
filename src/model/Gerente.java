package model;

import java.util.Objects;

public class Gerente implements Identifiable {
    private Integer id;
    private String nome;
    private String senha;

    private Integer companhiaId;
    private CompanhiaAerea companhia;

    public Gerente() {}

    public Gerente(Integer id, String nome, String senha, Integer companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

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
        if (!(o instanceof Gerente)) return false;
        Gerente gerente = (Gerente) o;
        if (this.id == null || gerente.id == null) return false;
        return Objects.equals(id, gerente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", companhiaId=" + companhiaId +
                '}';
    }
}
