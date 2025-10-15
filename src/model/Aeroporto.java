package model;

import java.util.Objects;

public class Aeroporto implements Identifiable {
    private Integer id;
    private String nome;
    private String localizacao;

    private Integer companhiaId;
    private CompanhiaAerea companhia;

    public Aeroporto() {}

    public Aeroporto(Integer id, String nome, String localizacao, Integer companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public Integer getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(Integer companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
