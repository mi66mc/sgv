package model;

public class Gerente implements Identifiable {
    private long id;
    private String nome;
    private String senha;

    private long companhiaId;
    private CompanhiaAerea companhia;

    public Gerente() {}

    public Gerente(long id, String nome, String senha, long companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public long getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(long companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }
}
